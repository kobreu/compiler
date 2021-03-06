/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class TableConstructorExp extends Exp {

  public TableConstructor tablecons;

  public TableConstructorExp (TableConstructor tablecons) {
    this.tablecons = tablecons;
    if (tablecons != null) tablecons.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (tablecons != null) tablecons.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (tablecons != null) tablecons.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (tablecons != null) tablecons.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TableConstructorExp(\n");
      if (tablecons != null)
        buffer.append(tablecons.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TableConstructorExp]");
    return buffer.toString();
  }
}
