/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public class LocalDecl extends Stat {

  public NameList namelist;
  public ExpList explist;

  public LocalDecl (NameList namelist, ExpList explist) {
    this.namelist = namelist;
    if (namelist != null) namelist.setParent(this);
    this.explist = explist;
    if (explist != null) explist.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (namelist != null) namelist.accept(visitor);
    if (explist != null) explist.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (namelist != null) namelist.traverseTopDown(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (namelist != null) namelist.traverseBottomUp(visitor);
    if (explist != null) explist.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("LocalDecl(\n");
      if (namelist != null)
        buffer.append(namelist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (explist != null)
        buffer.append(explist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [LocalDecl]");
    return buffer.toString();
  }
}
