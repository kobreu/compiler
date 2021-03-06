/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class FuncCallSelf extends FunctionCall {

  public PrefixExp preexp;
  public String name;
  public ExpList explist;

  public FuncCallSelf (PrefixExp preexp, String name, ExpList explist) {
    this.preexp = preexp;
    if (preexp != null) preexp.setParent(this);
    this.name = name;
    this.explist = explist;
    if (explist != null) explist.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (preexp != null) preexp.accept(visitor);
    if (explist != null) explist.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (preexp != null) preexp.traverseTopDown(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (preexp != null) preexp.traverseBottomUp(visitor);
    if (explist != null) explist.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncCallSelf(\n");
      if (preexp != null)
        buffer.append(preexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+name);
    buffer.append("\n");
      if (explist != null)
        buffer.append(explist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncCallSelf]");
    return buffer.toString();
  }
}
