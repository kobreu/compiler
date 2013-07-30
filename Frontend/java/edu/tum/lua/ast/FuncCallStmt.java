/*
 * Generated by classgen, version 1.5
 * 30.07.13 12:51
 */
package edu.tum.lua.ast;

public class FuncCallStmt extends Stat {

  public FunctionCall call;

  public FuncCallStmt (FunctionCall call) {
    this.call = call;
    if (call != null) call.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (call != null) call.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (call != null) call.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (call != null) call.traverseBottomUp(visitor);
    accept(visitor);
  }

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
