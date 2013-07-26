package serialization;

import edu.tum.lua.ast.Asm;
import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.BooleanExp;
import edu.tum.lua.ast.Closure;
import edu.tum.lua.ast.DoExp;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.Field;
import edu.tum.lua.ast.FieldExp;
import edu.tum.lua.ast.FieldLRExp;
import edu.tum.lua.ast.FieldList;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.ForExp;
import edu.tum.lua.ast.ForIn;
import edu.tum.lua.ast.FuncBody;
import edu.tum.lua.ast.FuncCall;
import edu.tum.lua.ast.FuncCallStmt;
import edu.tum.lua.ast.Function;
import edu.tum.lua.ast.FunctionCall;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.FunctionExp;
import edu.tum.lua.ast.IfThenElse;
import edu.tum.lua.ast.LastBreak;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.LastStat;
import edu.tum.lua.ast.LocalDecl;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.Name;
import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.Nil;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.ParList;
import edu.tum.lua.ast.PreExp;
import edu.tum.lua.ast.PrefixExp;
import edu.tum.lua.ast.PrefixExpExp;
import edu.tum.lua.ast.PrefixExpFuncCall;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.RepeatUntil;
import edu.tum.lua.ast.Stat;
import edu.tum.lua.ast.StatList;
import edu.tum.lua.ast.TableConstructor;
import edu.tum.lua.ast.TableConstructorExp;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Var;
import edu.tum.lua.ast.VarList;
import edu.tum.lua.ast.VarTabIndex;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.ast.VisitorNode;
import edu.tum.lua.ast.WhileExp;

public abstract class VisitorAdapterGeneric extends VisitorAdaptor {
	
	  @Override
	  public void visit(StatList statList) { visitGeneric(statList); }
	  @Override
	  public void visit(VarList varList) { visitGeneric(varList); }
	  @Override
	  public void visit(NameList nameList) {visitGeneric(nameList);  }
	  @Override
	  public void visit(ExpList expList) { visitGeneric(expList);  }
	  @Override
	  public void visit(FieldList fieldList) { visitGeneric(fieldList);  }
	  @Override
	  public void visit(Block block) {  visitGeneric(block);  }
	  @Override
	  public void visit(Name name) { visitGeneric(name); }
	  @Override
	  public void visit(FunctionCall functionCall) { visitGeneric(functionCall); }
	  @Override
	  public void visit(FuncBody body) { visitGeneric(body); }
	  @Override
	  public void visit(Function function) { visitGeneric(function); }
	  @Override
	  public void visit(FunctionExp functionExp) { visitGeneric(functionExp); }

	  @Override
	  public void visit(TableConstructor tableConstructor) { visitGeneric(tableConstructor); }
	  @Override
	  public void visit(LastStat lastStat) { visitGeneric(lastStat); }
	  @Override
	  public void visit(LastReturn lastReturn) { visitGeneric(lastReturn); }
	  @Override
	  public void visit(LastBreak lastBreak) { visitGeneric(lastBreak); }
	  @Override
	  public void visit(Stat stat) { visitGeneric(stat); }
	  @Override public void visit(Asm asm) { visitGeneric(asm); }
	  @Override public void visit(FuncCallStmt funcCallStmt) { visitGeneric(funcCallStmt); }
	  @Override public void visit(FuncCall funcCall) { visitGeneric(funcCall); }
	  @Override public void visit(DoExp doExp) { visitGeneric(doExp); }
	  @Override public void visit(WhileExp whileExp) { visitGeneric(whileExp); }
	  @Override public void visit(RepeatUntil repeatUntil) { visitGeneric(repeatUntil); }
	  @Override public void visit(IfThenElse ifThenElse) { visitGeneric(ifThenElse); }
	  @Override public void visit(ForExp forExp) { visitGeneric(forExp); }
	  @Override public void visit(ForIn forIn) { visitGeneric(forIn); }
	  @Override public void visit(FunctionDef functionDef) { visitGeneric(functionDef); }
	  @Override public void visit(LocalFuncDef localFuncDef) { visitGeneric(localFuncDef); }
	  @Override public void visit(LocalDecl localDecl) { visitGeneric(localDecl); }
	  @Override public void visit(Exp exp) { visitGeneric(exp);}
	  @Override public void visit(Nil nil) { visitGeneric(nil); }
	  @Override public void visit(BooleanExp booleanExp) { visitGeneric(booleanExp); }
	  @Override public void visit(NumberExp numberExp) { visitGeneric(numberExp); }
	  @Override public void visit(TextExp textExp) { visitGeneric(textExp); }
	  @Override public void visit(Dots dots) { visitGeneric(dots); }
	  @Override public void visit(Closure closure) { visitGeneric(closure); }
	  @Override public void visit(PreExp preExp) { visitGeneric(preExp); }
	  @Override public void visit(TableConstructorExp tableConstructorExp) { visitGeneric(tableConstructorExp); }
	  @Override public void visit(Binop binop) { visitGeneric(binop); }
	  @Override public void visit(Unop unop) { visitGeneric(unop); }
	  @Override public void visit(Var var) { visitGeneric(var); }
	  @Override public void visit(Variable variable) { visitGeneric(variable); }
	  @Override public void visit(VarTabIndex varTabIndex) { visitGeneric(varTabIndex);}
	  @Override public void visit(ParList parlist) { visitGeneric(parlist);}

	  @Override public void visit(PrefixExp prefixExp) { visitGeneric(prefixExp); }
	  @Override public void visit(PrefixExpVar prefixExpVar) { visitGeneric(prefixExpVar); }
	  @Override public void visit(PrefixExpFuncCall prefixExpFuncCall) { visitGeneric(prefixExpFuncCall); }
	  @Override public void visit(PrefixExpExp prefixExpExp) { visitGeneric(prefixExpExp); }
	  @Override public void visit(Field field) { visitGeneric(field); }
	  @Override public void visit(FieldLRExp fieldLRExp) { visitGeneric(fieldLRExp); }
	  @Override public void visit(FieldNameExp fieldNameExp) { visitGeneric(fieldNameExp); }
	  @Override public void visit(FieldExp fieldExp) { visitGeneric(fieldExp); }
	public void visitGeneric(VisitorNode node) {
	}

}
