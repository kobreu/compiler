/*
 * Generated by classgen, version 1.5
<<<<<<< HEAD
 * 23.07.13 15:35
=======
 * 24.07.13 15:58
>>>>>>> Intermediate
 */
package edu.tum.lua.ast;

public class FieldNameExp extends Field {

  public String ident;
  public Exp exp;

  public FieldNameExp (String ident, Exp exp) {
    this.ident = ident;
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
    buffer.append("FieldNameExp(\n");
    buffer.append("  "+tab+ident);
    buffer.append("\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FieldNameExp]");
    return buffer.toString();
  }
}
