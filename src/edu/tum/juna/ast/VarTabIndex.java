/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class VarTabIndex extends Var {

  public PrefixExp preexp;
  public Exp indexexp;

  public VarTabIndex (PrefixExp preexp, Exp indexexp) {
    this.preexp = preexp;
    if (preexp != null) preexp.setParent(this);
    this.indexexp = indexexp;
    if (indexexp != null) indexexp.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (preexp != null) preexp.accept(visitor);
    if (indexexp != null) indexexp.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (preexp != null) preexp.traverseTopDown(visitor);
    if (indexexp != null) indexexp.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (preexp != null) preexp.traverseBottomUp(visitor);
    if (indexexp != null) indexexp.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
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
