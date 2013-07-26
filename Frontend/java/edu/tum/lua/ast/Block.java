/*
 * Generated by classgen, version 1.5
 * 26.07.13 10:01
 */
package edu.tum.lua.ast;

public class Block implements SyntaxNode {

  private SyntaxNode parent;
  public StatList stats;
  public LastStat last;

  public Block (StatList stats, LastStat last) {
    this.stats = stats;
    if (stats != null) stats.setParent(this);
    this.last = last;
    if (last != null) last.setParent(this);
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (stats != null) stats.accept(visitor);
    if (last != null) last.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (stats != null) stats.traverseTopDown(visitor);
    if (last != null) last.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (stats != null) stats.traverseBottomUp(visitor);
    if (last != null) last.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Block(\n");
      if (stats != null)
        buffer.append(stats.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (last != null)
        buffer.append(last.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Block]");
    return buffer.toString();
  }
}
