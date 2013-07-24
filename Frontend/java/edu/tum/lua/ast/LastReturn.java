/*
 * Generated by classgen, version 1.5
 * 23.07.13 15:35
 */
package edu.tum.lua.ast;

public class LastReturn extends LastStat {

  public ExpList explist;

  public LastReturn (ExpList explist) {
    this.explist = explist;
    if (explist != null) explist.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (explist != null) explist.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (explist != null) explist.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("LastReturn(\n");
      if (explist != null)
        buffer.append(explist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [LastReturn]");
    return buffer.toString();
  }
}