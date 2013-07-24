/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public interface Visitor {

  public void visit(Op op);
  public void visit(StatList statList);
  public void visit(VarList varList);
  public void visit(NameList nameList);
  public void visit(ExpList expList);
  public void visit(FieldList fieldList);
  public void visit(Chunk chunk);
  public void visit(Block block);
  public void visit(Name name);
  public void visit(FunctionCall functionCall);
  public void visit(TableConstructor tableConstructor);
  public void visit(LastStat lastStat);
  public void visit(LastReturn lastReturn);
  public void visit(LastBreak lastBreak);
  public void visit(Stat stat);
  public void visit(Asm asm);
  public void visit(FuncCallStmt funcCallStmt);
  public void visit(DoExp doExp);
  public void visit(WhileExp whileExp);
  public void visit(RepeatUntil repeatUntil);
  public void visit(IfThenElse ifThenElse);
  public void visit(ForExp forExp);
  public void visit(ForIn forIn);
  public void visit(FunctionDef functionDef);
  public void visit(LocalFuncDef localFuncDef);
  public void visit(LocalDecl localDecl);
  public void visit(Exp exp);
  public void visit(Nil nil);
  public void visit(BooleanExp booleanExp);
  public void visit(NumberExp numberExp);
  public void visit(TextExp textExp);
  public void visit(Dots dots);
  public void visit(Closure closure);
  public void visit(PreExp preExp);
  public void visit(TableConstructorExp tableConstructorExp);
  public void visit(Binop binop);
  public void visit(Unop unop);
  public void visit(Var var);
  public void visit(Variable variable);
  public void visit(VarTabIndex varTabIndex);
  public void visit(PrefixExp prefixExp);
  public void visit(PrefixExpVar prefixExpVar);
  public void visit(PrefixExpFuncCall prefixExpFuncCall);
  public void visit(PrefixExpExp prefixExpExp);
  public void visit(Field field);
  public void visit(FieldLRExp fieldLRExp);
  public void visit(FieldNameExp fieldNameExp);
  public void visit(FieldExp fieldExp);

}
