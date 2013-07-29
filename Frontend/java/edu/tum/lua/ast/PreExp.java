/*
 * Generated by classgen, version 1.5
 * 29.07.13 14:43
 */
package edu.tum.lua.ast;

public class PreExp extends Exp {

  public PrefixExp preexp;

  public PreExp (PrefixExp preexp) {
    this.preexp = preexp;
    if (preexp != null) preexp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (preexp != null) preexp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (preexp != null) preexp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (preexp != null) preexp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("PreExp(\n");
      if (preexp != null)
        buffer.append(preexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [PreExp]");
    return buffer.toString();
  }
}
