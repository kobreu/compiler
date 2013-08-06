/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
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

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (exp != null) exp.accept(visitor);
    if (block != null) block.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
    if (block != null) block.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (exp != null) exp.traverseBottomUp(visitor);
    if (block != null) block.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
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
