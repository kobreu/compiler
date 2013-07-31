/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public class FieldExp extends Field {

  public Exp fieldexp;

  public FieldExp (Exp fieldexp) {
    this.fieldexp = fieldexp;
    if (fieldexp != null) fieldexp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (fieldexp != null) fieldexp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (fieldexp != null) fieldexp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (fieldexp != null) fieldexp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FieldExp(\n");
      if (fieldexp != null)
        buffer.append(fieldexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FieldExp]");
    return buffer.toString();
  }
}
