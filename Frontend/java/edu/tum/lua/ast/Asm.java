/*
 * Generated by classgen, version 1.5
 * 26.07.13 10:01
 */
package edu.tum.lua.ast;

public class Asm extends Stat {

  public VarList varlist;
  public ExpList explist;

  public Asm (VarList varlist, ExpList explist) {
    this.varlist = varlist;
    if (varlist != null) varlist.setParent(this);
    this.explist = explist;
    if (explist != null) explist.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (varlist != null) varlist.accept(visitor);
    if (explist != null) explist.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (varlist != null) varlist.traverseTopDown(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (varlist != null) varlist.traverseBottomUp(visitor);
    if (explist != null) explist.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Asm(\n");
      if (varlist != null)
        buffer.append(varlist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (explist != null)
        buffer.append(explist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Asm]");
    return buffer.toString();
  }
}
