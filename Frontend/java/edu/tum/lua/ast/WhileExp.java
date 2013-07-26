/*
 * Generated by classgen, version 1.5
 * 26.07.13 09:18
 */
package edu.tum.lua.ast;

public class WhileExp extends Stat {

  public Exp exp;
  public Block block;

  public WhileExp (Exp exp, Block block) {
    this.exp = exp;
    if (exp != null) exp.setParent(this);
    this.block = block;
    if (block != null) block.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (exp != null) exp.accept(visitor);
    if (block != null) block.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
    if (block != null) block.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (exp != null) exp.traverseBottomUp(visitor);
    if (block != null) block.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("WhileExp(\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (block != null)
        buffer.append(block.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [WhileExp]");
    return buffer.toString();
  }
}
