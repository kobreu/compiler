/*
 * Generated by classgen, version 1.5
 * 29.07.13 14:43
 */
package edu.tum.lua.ast;

public class FuncCall extends FunctionCall {

  public PrefixExp preexp;
  public ExpList explist;

  public FuncCall (PrefixExp preexp, ExpList explist) {
    this.preexp = preexp;
    if (preexp != null) preexp.setParent(this);
    this.explist = explist;
    if (explist != null) explist.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (preexp != null) preexp.accept(visitor);
    if (explist != null) explist.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (preexp != null) preexp.traverseTopDown(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (preexp != null) preexp.traverseBottomUp(visitor);
    if (explist != null) explist.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncCall(\n");
      if (preexp != null)
        buffer.append(preexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (explist != null)
        buffer.append(explist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncCall]");
    return buffer.toString();
  }
}
