package serialization;

import edu.tum.lua.ast.Asm;
import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.BooleanExp;
import edu.tum.lua.ast.Chunk;
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
import edu.tum.lua.ast.FuncCallStmt;
import edu.tum.lua.ast.FunctionCall;
import edu.tum.lua.ast.FunctionDef;
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
	
	  public void visit(StatList statList) { visitGeneric(statList); }
	  public void visit(VarList varList) { visitGeneric(varList); }
	  public void visit(NameList nameList) {visitGeneric(nameList);  }
	  public void visit(ExpList expList) { visitGeneric(expList);  }
	  public void visit(FieldList fieldList) { visitGeneric(fieldList);  }
	  public void visit(Chunk chunk) { visitGeneric(chunk);  }
	  public void visit(Block block) {  visitGeneric(block);  }
	  public void visit(Name name) { visitGeneric(name); }
	  public void visit(FunctionCall functionCall) { visitGeneric(functionCall); }
	  public void visit(TableConstructor tableConstructor) { visitGeneric(tableConstructor); }
	  public void visit(LastStat lastStat) { visitGeneric(lastStat); }
	  public void visit(LastReturn lastReturn) { visitGeneric(lastReturn); }
	  public void visit(LastBreak lastBreak) { visitGeneric(lastBreak); }
	  public void visit(Stat stat) { visitGeneric(stat); }
	  public void visit(Asm asm) { visitGeneric(asm); }
	  public void visit(FuncCallStmt funcCallStmt) { visitGeneric(funcCallStmt); }
	  public void visit(DoExp doExp) { visitGeneric(doExp); }
	  public void visit(WhileExp whileExp) { visitGeneric(whileExp); }
	  public void visit(RepeatUntil repeatUntil) { visitGeneric(repeatUntil); }
	  public void visit(IfThenElse ifThenElse) { visitGeneric(ifThenElse); }
	  public void visit(ForExp forExp) { visitGeneric(forExp); }
	  public void visit(ForIn forIn) { visitGeneric(forIn); }
	  public void visit(FunctionDef functionDef) { visitGeneric(functionDef); }
	  public void visit(LocalFuncDef localFuncDef) { visitGeneric(localFuncDef); }
	  public void visit(LocalDecl localDecl) { visitGeneric(localDecl); }
	  public void visit(Exp exp) { visitGeneric(exp);}
	  public void visit(Nil nil) { visitGeneric(nil); }
	  public void visit(BooleanExp booleanExp) { visitGeneric(booleanExp); }
	  public void visit(NumberExp numberExp) { visitGeneric(numberExp); }
	  public void visit(TextExp textExp) { visitGeneric(textExp); }
	  public void visit(Dots dots) { visitGeneric(dots); }
	  public void visit(Closure closure) { visitGeneric(closure); }
	  public void visit(PreExp preExp) { visitGeneric(preExp); }
	  public void visit(TableConstructorExp tableConstructorExp) { visitGeneric(tableConstructorExp); }
	  public void visit(Binop binop) { visitGeneric(binop); }
	  public void visit(Unop unop) { visitGeneric(unop); }
	  public void visit(Var var) { visitGeneric(var); }
	  public void visit(Variable variable) { visitGeneric(variable); }
	  public void visit(VarTabIndex varTabIndex) { visitGeneric(varTabIndex);}
	  public void visit(PrefixExp prefixExp) { visitGeneric(prefixExp); }
	  public void visit(PrefixExpVar prefixExpVar) { visitGeneric(prefixExpVar); }
	  public void visit(PrefixExpFuncCall prefixExpFuncCall) { visitGeneric(prefixExpFuncCall); }
	  public void visit(PrefixExpExp prefixExpExp) { visitGeneric(prefixExpExp); }
	  public void visit(Field field) { visitGeneric(field); }
	  public void visit(FieldLRExp fieldLRExp) { visitGeneric(fieldLRExp); }
	  public void visit(FieldNameExp fieldNameExp) { visitGeneric(fieldNameExp); }
	  public void visit(FieldExp fieldExp) { visitGeneric(fieldExp); }
	public void visitGeneric(VisitorNode node) {
	}

}
