/*
 * Generated by classgen, version 1.5
 * 30.07.13 12:11
 */
package edu.tum.lua.ast;

public class IfThenElse extends Stat {

  public Exp ifexp;
  public Block thenblock;
  public Block elseblock;

  public IfThenElse (Exp ifexp, Block thenblock, Block elseblock) {
    this.ifexp = ifexp;
    if (ifexp != null) ifexp.setParent(this);
    this.thenblock = thenblock;
    if (thenblock != null) thenblock.setParent(this);
    this.elseblock = elseblock;
    if (elseblock != null) elseblock.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (ifexp != null) ifexp.accept(visitor);
    if (thenblock != null) thenblock.accept(visitor);
    if (elseblock != null) elseblock.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (ifexp != null) ifexp.traverseTopDown(visitor);
    if (thenblock != null) thenblock.traverseTopDown(visitor);
    if (elseblock != null) elseblock.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (ifexp != null) ifexp.traverseBottomUp(visitor);
    if (thenblock != null) thenblock.traverseBottomUp(visitor);
    if (elseblock != null) elseblock.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("IfThenElse(\n");
      if (ifexp != null)
        buffer.append(ifexp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (thenblock != null)
        buffer.append(thenblock.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (elseblock != null)
        buffer.append(elseblock.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [IfThenElse]");
    return buffer.toString();
  }
}
