/*
 * Generated by classgen, version 1.5
 * 23.07.13 15:35
 */
package edu.tum.lua.ast;

public class FunctionExp extends Exp {

  public Function function;

  public FunctionExp (Function function) {
    this.function = function;
    if (function != null) function.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (function != null) function.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (function != null) function.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (function != null) function.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FunctionExp(\n");
      if (function != null)
        buffer.append(function.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FunctionExp]");
    return buffer.toString();
  }
}
