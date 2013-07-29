/*
 * Generated by classgen, version 1.5
 * 29.07.13 14:43
 */
package edu.tum.lua.ast;

public class PrefixExpExp extends PrefixExp {

  public Exp exp;

  public PrefixExpExp (Exp exp) {
    this.exp = exp;
    if (exp != null) exp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (exp != null) exp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (exp != null) exp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("PrefixExpExp(\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [PrefixExpExp]");
    return buffer.toString();
  }
}
