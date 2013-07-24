/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public class PrefixExpVar extends PrefixExp {

  public Var var;

  public PrefixExpVar (Var var) {
    this.var = var;
    if (var != null) var.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (var != null) var.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (var != null) var.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (var != null) var.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("PrefixExpVar(\n");
      if (var != null)
        buffer.append(var.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [PrefixExpVar]");
    return buffer.toString();
  }
}
