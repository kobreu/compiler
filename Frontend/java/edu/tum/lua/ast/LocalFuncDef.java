/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public class LocalFuncDef extends Stat {

  public String ident;
  public NameList args;
  public boolean varargs;
  public Block block;

  public LocalFuncDef (String ident, NameList args, boolean varargs, Block block) {
    this.ident = ident;
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
    if (args != null) args.accept(visitor);
    if (block != null) block.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (args != null) args.traverseTopDown(visitor);
    if (block != null) block.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (args != null) args.traverseBottomUp(visitor);
    if (block != null) block.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("LocalFuncDef(\n");
    buffer.append("  "+tab+ident);
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
    buffer.append(") [LocalFuncDef]");
    return buffer.toString();
  }
}
