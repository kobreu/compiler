package edu.tum.juna.parser.serialization;

import edu.tum.juna.ast.Asm;
import edu.tum.juna.ast.Binop;
import edu.tum.juna.ast.Block;
import edu.tum.juna.ast.BooleanExp;
import edu.tum.juna.ast.DoExp;
import edu.tum.juna.ast.Dots;
import edu.tum.juna.ast.Exp;
import edu.tum.juna.ast.ExpList;
import edu.tum.juna.ast.Field;
import edu.tum.juna.ast.FieldExp;
import edu.tum.juna.ast.FieldLRExp;
import edu.tum.juna.ast.FieldList;
import edu.tum.juna.ast.FieldNameExp;
import edu.tum.juna.ast.ForExp;
import edu.tum.juna.ast.ForIn;
import edu.tum.juna.ast.FuncCall;
import edu.tum.juna.ast.FuncCallStmt;
import edu.tum.juna.ast.FuncName;
import edu.tum.juna.ast.FuncNameDDotVar;
import edu.tum.juna.ast.FuncNameVar;
import edu.tum.juna.ast.FuncNameVarDotFuncName;
import edu.tum.juna.ast.FunctionCall;
import edu.tum.juna.ast.FunctionExp;
import edu.tum.juna.ast.IfThenElse;
import edu.tum.juna.ast.LastBreak;
import edu.tum.juna.ast.LastReturn;
import edu.tum.juna.ast.LastStat;
import edu.tum.juna.ast.LocalDecl;
import edu.tum.juna.ast.LocalFuncDef;
import edu.tum.juna.ast.Name;
import edu.tum.juna.ast.NameList;
import edu.tum.juna.ast.Nil;
import edu.tum.juna.ast.NumberExp;
import edu.tum.juna.ast.PreExp;
import edu.tum.juna.ast.PrefixExp;
import edu.tum.juna.ast.PrefixExpExp;
import edu.tum.juna.ast.PrefixExpFuncCall;
import edu.tum.juna.ast.PrefixExpVar;
import edu.tum.juna.ast.RepeatUntil;
import edu.tum.juna.ast.Stat;
import edu.tum.juna.ast.StatList;
import edu.tum.juna.ast.TableConstructor;
import edu.tum.juna.ast.TableConstructorExp;
import edu.tum.juna.ast.TextExp;
import edu.tum.juna.ast.Unop;
import edu.tum.juna.ast.Var;
import edu.tum.juna.ast.VarList;
import edu.tum.juna.ast.VarTabIndex;
import edu.tum.juna.ast.Variable;
import edu.tum.juna.ast.VisitorAdaptor;
import edu.tum.juna.ast.VisitorNode;
import edu.tum.juna.ast.WhileExp;

public abstract class VisitorAdapterGeneric extends VisitorAdaptor {

	@Override
	public void visit(StatList statList) {
		visitGeneric(statList);
	}

	@Override
	public void visit(VarList varList) {
		visitGeneric(varList);
	}

	@Override
	public void visit(NameList nameList) {
		visitGeneric(nameList);
	}

	@Override
	public void visit(ExpList expList) {
		visitGeneric(expList);
	}

	@Override
	public void visit(FieldList fieldList) {
		visitGeneric(fieldList);
	}

	@Override
	public void visit(Block block) {
		visitGeneric(block);
	}

	@Override
	public void visit(Name name) {
		visitGeneric(name);
	}

	@Override
	public void visit(FunctionCall functionCall) {
		visitGeneric(functionCall);
	}

	@Override
	public void visit(FunctionExp functionExp) {
		visitGeneric(functionExp);
	}

	@Override
	public void visit(TableConstructor tableConstructor) {
		visitGeneric(tableConstructor);
	}

	@Override
	public void visit(LastStat lastStat) {
		visitGeneric(lastStat);
	}

	@Override
	public void visit(LastReturn lastReturn) {
		visitGeneric(lastReturn);
	}

	@Override
	public void visit(LastBreak lastBreak) {
		visitGeneric(lastBreak);
	}

	@Override
	public void visit(Stat stat) {
		visitGeneric(stat);
	}

	@Override
	public void visit(Asm asm) {
		visitGeneric(asm);
	}

	@Override
	public void visit(FuncCallStmt funcCallStmt) {
		visitGeneric(funcCallStmt);
	}

	@Override
	public void visit(FuncCall funcCall) {
		visitGeneric(funcCall);
	}

	@Override
	public void visit(DoExp doExp) {
		visitGeneric(doExp);
	}

	@Override
	public void visit(WhileExp whileExp) {
		visitGeneric(whileExp);
	}

	@Override
	public void visit(RepeatUntil repeatUntil) {
		visitGeneric(repeatUntil);
	}

	@Override
	public void visit(IfThenElse ifThenElse) {
		visitGeneric(ifThenElse);
	}

	@Override
	public void visit(ForExp forExp) {
		visitGeneric(forExp);
	}

	@Override
	public void visit(ForIn forIn) {
		visitGeneric(forIn);
	}

	@Override
	public void visit(FuncName funcName) {
		visitGeneric(funcName);
	}

	@Override
	public void visit(FuncNameVar funcNameVar) {
		visitGeneric(funcNameVar);
	}

	@Override
	public void visit(FuncNameDDotVar funcNameVar) {
		visitGeneric(funcNameVar);
	}

	@Override
	public void visit(FuncNameVarDotFuncName funcNameVar) {
		visitGeneric(funcNameVar);
	}

	@Override
	public void visit(LocalFuncDef localFuncDef) {
		visitGeneric(localFuncDef);
	}

	@Override
	public void visit(LocalDecl localDecl) {
		visitGeneric(localDecl);
	}

	@Override
	public void visit(Exp exp) {
		visitGeneric(exp);
	}

	@Override
	public void visit(Nil nil) {
		visitGeneric(nil);
	}

	@Override
	public void visit(BooleanExp booleanExp) {
		visitGeneric(booleanExp);
	}

	@Override
	public void visit(NumberExp numberExp) {
		visitGeneric(numberExp);
	}

	@Override
	public void visit(TextExp textExp) {
		visitGeneric(textExp);
	}

	@Override
	public void visit(Dots dots) {
		visitGeneric(dots);
	}

	@Override
	public void visit(PreExp preExp) {
		visitGeneric(preExp);
	}

	@Override
	public void visit(TableConstructorExp tableConstructorExp) {
		visitGeneric(tableConstructorExp);
	}

	@Override
	public void visit(Binop binop) {
		visitGeneric(binop);
	}

	@Override
	public void visit(Unop unop) {
		visitGeneric(unop);
	}

	@Override
	public void visit(Var var) {
		visitGeneric(var);
	}

	@Override
	public void visit(Variable variable) {
		visitGeneric(variable);
	}

	@Override
	public void visit(VarTabIndex varTabIndex) {
		visitGeneric(varTabIndex);
	}

	@Override
	public void visit(PrefixExp prefixExp) {
		visitGeneric(prefixExp);
	}

	@Override
	public void visit(PrefixExpVar prefixExpVar) {
		visitGeneric(prefixExpVar);
	}

	@Override
	public void visit(PrefixExpFuncCall prefixExpFuncCall) {
		visitGeneric(prefixExpFuncCall);
	}

	@Override
	public void visit(PrefixExpExp prefixExpExp) {
		visitGeneric(prefixExpExp);
	}

	@Override
	public void visit(Field field) {
		visitGeneric(field);
	}

	@Override
	public void visit(FieldLRExp fieldLRExp) {
		visitGeneric(fieldLRExp);
	}

	@Override
	public void visit(FieldNameExp fieldNameExp) {
		visitGeneric(fieldNameExp);
	}

	@Override
	public void visit(FieldExp fieldExp) {
		visitGeneric(fieldExp);
	}

	@SuppressWarnings("unused")
	public void visitGeneric(VisitorNode node) {
	}

}
