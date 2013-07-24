/*
 * Generated by classgen, version 1.5
 * 24.07.13 15:58
 */
package edu.tum.lua.ast;

public class ForExp extends Stat {

  public String ident;
  public Exp start;
  public Exp end;
  public Exp step;
  public Block block;

  public ForExp (String ident, Exp start, Exp end, Exp step, Block block) {
    this.ident = ident;
    this.start = start;
    if (start != null) start.setParent(this);
    this.end = end;
    if (end != null) end.setParent(this);
    this.step = step;
    if (step != null) step.setParent(this);
    this.block = block;
    if (block != null) block.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (start != null) start.accept(visitor);
    if (end != null) end.accept(visitor);
    if (step != null) step.accept(visitor);
    if (block != null) block.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (start != null) start.traverseTopDown(visitor);
    if (end != null) end.traverseTopDown(visitor);
    if (step != null) step.traverseTopDown(visitor);
    if (block != null) block.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (start != null) start.traverseBottomUp(visitor);
    if (end != null) end.traverseBottomUp(visitor);
    if (step != null) step.traverseBottomUp(visitor);
    if (block != null) block.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ForExp(\n");
    buffer.append("  "+tab+ident);
    buffer.append("\n");
      if (start != null)
        buffer.append(start.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (end != null)
        buffer.append(end.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (step != null)
        buffer.append(step.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (block != null)
        buffer.append(block.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ForExp]");
    return buffer.toString();
  }
}
