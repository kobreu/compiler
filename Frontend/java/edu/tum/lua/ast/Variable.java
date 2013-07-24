/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public class Variable extends Var {

  public String var;

  public Variable (String var) {
    this.var = var;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Variable(\n");
    buffer.append("  "+tab+var);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Variable]");
    return buffer.toString();
  }
}
