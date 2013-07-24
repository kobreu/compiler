/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public class VarTabIndex extends Var {

  public PrefixExp preexp;
  public Exp indexexp;

  public VarTabIndex (PrefixExp preexp, Exp indexexp) {
    this.preexp = preexp;
    if (preexp != null) preexp.setParent(this);
    this.indexexp = indexexp;
    if (indexexp != null) indexexp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (preexp != null) preexp.accept(visitor);
    if (indexexp != null) indexexp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (preexp != null) preexp.traverseTopDown(visitor);
    if (indexexp != null) indexexp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (preexp != null) preexp.traverseBottomUp(visitor);
    if (indexexp != null) indexexp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("VarTabIndex(\n");
      if (preexp != null)
        buffer.append(preexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (indexexp != null)
        buffer.append(indexexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [VarTabIndex]");
    return buffer.toString();
  }
}
