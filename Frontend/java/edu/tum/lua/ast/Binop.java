/*
 * Generated by classgen, version 1.5
 * 26.07.13 09:18
 */
package edu.tum.lua.ast;

public class Binop extends Exp {

  public Exp leftexp;
  public int op;
  public Exp rightexp;

  public Binop (Exp leftexp, int op, Exp rightexp) {
    this.leftexp = leftexp;
    if (leftexp != null) leftexp.setParent(this);
    this.op = op;
    this.rightexp = rightexp;
    if (rightexp != null) rightexp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (leftexp != null) leftexp.accept(visitor);
    if (rightexp != null) rightexp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (leftexp != null) leftexp.traverseTopDown(visitor);
    if (rightexp != null) rightexp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (leftexp != null) leftexp.traverseBottomUp(visitor);
    if (rightexp != null) rightexp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Binop(\n");
      if (leftexp != null)
        buffer.append(leftexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+op);
    buffer.append("\n");
      if (rightexp != null)
        buffer.append(rightexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Binop]");
    return buffer.toString();
  }
}
