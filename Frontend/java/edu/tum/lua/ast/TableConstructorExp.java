/*
 * Generated by classgen, version 1.5
 * 26.07.13 09:18
 */
package edu.tum.lua.ast;

public class TableConstructorExp extends Exp {

  public TableConstructor tablecons;

  public TableConstructorExp (TableConstructor tablecons) {
    this.tablecons = tablecons;
    if (tablecons != null) tablecons.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (tablecons != null) tablecons.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (tablecons != null) tablecons.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (tablecons != null) tablecons.traverseBottomUp(visitor);
    accept(visitor);
  }

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
