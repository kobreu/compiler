/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

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

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (ifexp != null) ifexp.accept(visitor);
    if (thenblock != null) thenblock.accept(visitor);
    if (elseblock != null) elseblock.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (ifexp != null) ifexp.traverseTopDown(visitor);
    if (thenblock != null) thenblock.traverseTopDown(visitor);
    if (elseblock != null) elseblock.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (ifexp != null) ifexp.traverseBottomUp(visitor);
    if (thenblock != null) thenblock.traverseBottomUp(visitor);
    if (elseblock != null) elseblock.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
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
