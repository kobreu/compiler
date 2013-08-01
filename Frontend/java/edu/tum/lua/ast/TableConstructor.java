/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public class TableConstructor extends SyntaxNode {

  private SyntaxNode parent;
  public FieldList fieldlist;

  public TableConstructor (FieldList fieldlist) {
    this.fieldlist = fieldlist;
    if (fieldlist != null) fieldlist.setParent(this);
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
    if (fieldlist != null) fieldlist.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (fieldlist != null) fieldlist.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (fieldlist != null) fieldlist.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TableConstructor(\n");
      if (fieldlist != null)
        buffer.append(fieldlist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TableConstructor]");
    return buffer.toString();
  }
}