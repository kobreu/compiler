/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class FuncCallStmt extends Stat {

  public FunctionCall call;

  public FuncCallStmt (FunctionCall call) {
    this.call = call;
    if (call != null) call.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (call != null) call.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (call != null) call.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (call != null) call.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncCallStmt(\n");
      if (call != null)
        buffer.append(call.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncCallStmt]");
    return buffer.toString();
  }
}
