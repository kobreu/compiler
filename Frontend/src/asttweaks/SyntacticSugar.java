package asttweaks;

import serialization.VisitorAdapterGeneric;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.FuncName;
import edu.tum.lua.ast.FuncNameDDotVar;
import edu.tum.lua.ast.FuncNameVar;
import edu.tum.lua.ast.FuncNameVarDotFuncName;
import edu.tum.lua.ast.FunctionExp;
import edu.tum.lua.ast.Name;
import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.PreExp;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.Var;
import edu.tum.lua.ast.VarList;
import edu.tum.lua.ast.VarTabIndex;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.ast.VisitorNode;

public class SyntacticSugar {

	private static final class FuncNameToPrefixExpVisitor extends
			VisitorAdaptor {
		public Var var;

		@Override
		public void visit(FuncNameDDotVar funcNameDDotVar) {
			VarTabIndex head = new VarTabIndex(new PrefixExpVar(new Variable(
					funcNameDDotVar.funcname.name)), new PreExp(
					new PrefixExpVar(new Variable(
							funcNameDDotVar.selffuncname.name))));
			var = head;
		}

		@Override
		public void visit(FuncNameVar funcNameVar) {
			var = new Variable(funcNameVar.name.name);
		}
		
		@Override
		public void visit(FuncNameVarDotFuncName funcNameVarDotFuncName) {
			VarTabIndex newHead = new VarTabIndex(new PrefixExpVar(
					new Variable(funcNameVarDotFuncName.name.name)), null);
			funcNameVarDotFuncName.funcnamelist.accept(this);
			newHead.indexexp = new PreExp(new PrefixExpVar(var));
			var = newHead;
		}
	}

	private static final class DoesFuncNameHaveADDotCall extends
			VisitorAdaptor {

		public boolean ddotcall = false;

		@Override
		public void visit(FuncNameDDotVar funcNameDDotVar) {
			ddotcall = true;
		}

		@Override
		public void visit(FuncNameVar funcNameVar) {
		}

		@Override
		public void visit(FuncNameVarDotFuncName funcNameVarDotFuncName) {
			funcNameVarDotFuncName.childrenAccept(this);
		}
	}

	public static VarList funcNameToVarList(FuncName fn) {
		VarList vl = new VarList();
		if (fn instanceof FuncNameVar) {
			vl.append(new Variable(((FuncNameVar) fn).name.name));
			return vl;
		} else if (fn instanceof FuncNameDDotVar) {
			FuncNameDDotVar ddotVar = (FuncNameDDotVar) fn;
			VarTabIndex head = new VarTabIndex(new PrefixExpVar(new Variable(
					ddotVar.funcname.name)), new PreExp(new PrefixExpVar(
					new Variable(ddotVar.selffuncname.name))));
			vl.append(head);
			return vl;
		} else if (fn instanceof FuncNameVarDotFuncName) {
			// construct a tree

			FuncNameToPrefixExpVisitor ad = new FuncNameToPrefixExpVisitor();
			fn.accept(ad);
			vl.append(ad.var);
			return vl;

		} else {
			throw new RuntimeException("Should not be here!!!");
		}
	}

	public static ExpList methodDefinitionToExpression(FuncName fn, FuncBody fb) {
		ExpList ex = new ExpList();
		NameList nl = fb.getArgs();

		// does the funcname contain a : ? If yes, add self as first argument
		DoesFuncNameHaveADDotCall d = new DoesFuncNameHaveADDotCall();
		fn.accept(d);
		if(d.ddotcall) {
			final NameList newnl = new NameList(new Name("self"));
			nl.childrenAccept(new VisitorAdapterGeneric() {
					@Override
					public void visitGeneric(VisitorNode node) {
						newnl.append((Name) node);
					}
			});
			nl = newnl;
		}
		ex.append(new FunctionExp(nl, fb.getVarArgs(), fb.getBlock()));
		return ex;
	}

}
