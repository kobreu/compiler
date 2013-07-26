/*
 * Generated by classgen, version 1.5
 * 26.07.13 09:18
 */
package edu.tum.lua.ast;

public class ForIn extends Stat {

  public NameList namelist;
  public ExpList explist;
  public Block block;

  public ForIn (NameList namelist, ExpList explist, Block block) {
    this.namelist = namelist;
    if (namelist != null) namelist.setParent(this);
    this.explist = explist;
    if (explist != null) explist.setParent(this);
    this.block = block;
    if (block != null) block.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (namelist != null) namelist.accept(visitor);
    if (explist != null) explist.accept(visitor);
    if (block != null) block.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (namelist != null) namelist.traverseTopDown(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
    if (block != null) block.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (namelist != null) namelist.traverseBottomUp(visitor);
    if (explist != null) explist.traverseBottomUp(visitor);
    if (block != null) block.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ForIn(\n");
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
      if (block != null)
        buffer.append(block.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ForIn]");
    return buffer.toString();
  }
}
