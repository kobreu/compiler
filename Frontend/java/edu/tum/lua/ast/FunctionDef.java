/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public class FunctionDef extends Stat {

  public String ident;
  public NameList members;
  public NameList args;
  public boolean varargs;
  public Block block;

  public FunctionDef (String ident, NameList members, NameList args, boolean varargs, Block block) {
    this.ident = ident;
    this.members = members;
    if (members != null) members.setParent(this);
    this.args = args;
    if (args != null) args.setParent(this);
    this.varargs = varargs;
    this.block = block;
    if (block != null) block.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (members != null) members.accept(visitor);
    if (args != null) args.accept(visitor);
    if (block != null) block.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (members != null) members.traverseTopDown(visitor);
    if (args != null) args.traverseTopDown(visitor);
    if (block != null) block.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (members != null) members.traverseBottomUp(visitor);
    if (args != null) args.traverseBottomUp(visitor);
    if (block != null) block.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FunctionDef(\n");
    buffer.append("  "+tab+ident);
    buffer.append("\n");
      if (members != null)
        buffer.append(members.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (args != null)
        buffer.append(args.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+varargs);
    buffer.append("\n");
      if (block != null)
        buffer.append(block.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FunctionDef]");
    return buffer.toString();
  }
}
