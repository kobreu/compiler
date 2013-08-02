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
import edu.tum.lua.ast.PrefixExp;
import edu.tum.lua.ast.PrefixExpExp;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.TextExp;
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
		
		public PrefixExp prefixexp;

		@Override
		public void visit(FuncNameDDotVar funcNameDDotVar) {
			Variable var2 = new Variable(
					funcNameDDotVar.selffuncname.name);
			var2.setStart(funcNameDDotVar.selffuncname.getStart());
			var2.setEnd(funcNameDDotVar.selffuncname.getEnd());
			PrefixExp prefixExp = new PrefixExpVar(var2);
			prefixExp.setStart(var2.getStart());
			prefixExp.setEnd(var2.getEnd());
			
			TextExp selfvar = new TextExp(funcNameDDotVar.funcname.name);
			selfvar.setStart(funcNameDDotVar.funcname.getStart());
			selfvar.setEnd(funcNameDDotVar.funcname.getEnd());
			
			VarTabIndex head = new VarTabIndex(prefixExp, selfvar);
			head.setStart(prefixExp.getStart());
			head.setStart(selfvar.getEnd());
			
			var = head;
		}

		@Override
		public void visit(FuncNameVar funcNameVar) {
			// TODO not correct
			prefixexp = new PrefixExpExp(new TextExp(funcNameVar.name.name));
		}
		
		@Override
		public void visit(FuncNameVarDotFuncName funcNameVarDotFuncName) {
			if(funcNameVarDotFuncName.funcnamelist instanceof FuncNameVar) {
				FuncNameVar fn = (FuncNameVar) funcNameVarDotFuncName.funcnamelist;
				VarTabIndex newHead = new VarTabIndex(new PrefixExpVar(new Variable(funcNameVarDotFuncName.name.name)), new TextExp(fn.name.name));
				var = newHead;
			} else {
				// TODO not correct
				VarTabIndex newHead = new VarTabIndex(new PrefixExpVar(
						new Variable(funcNameVarDotFuncName.name.name)), null);
				newHead.setStart(funcNameVarDotFuncName.getStart());
				newHead.setEnd(funcNameVarDotFuncName.getEnd());
				funcNameVarDotFuncName.funcnamelist.accept(this);
				newHead.indexexp = new PreExp(new PrefixExpVar(var));
				var = newHead;
			}
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
			Variable var = new Variable(((FuncNameVar) fn).name.name);
			var.setStart(((FuncNameVar) fn).getStart());
			var.setEnd(((FuncNameVar) fn).getEnd());

			vl.append(var);
			return vl;
		} else if (fn instanceof FuncNameDDotVar) {
			FuncNameDDotVar ddotVar = (FuncNameDDotVar) fn;
			VarTabIndex head = new VarTabIndex(new PrefixExpVar(new Variable(
					ddotVar.selffuncname.name)), new TextExp(ddotVar.funcname.name));
			head.setStart(ddotVar.getStart());
			head.setEnd(ddotVar.getEnd());
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
		FunctionExp exp = new FunctionExp(nl, fb.getVarArgs(), fb.getBlock());
		exp.setStart(nl.getStart());
		exp.setEnd(fb.getBlock().getEnd());
		ex.append(exp);
		return ex;
	}

}
