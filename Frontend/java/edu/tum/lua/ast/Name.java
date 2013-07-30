/*
 * Generated by classgen, version 1.5
 * 30.07.13 12:51
 */
package edu.tum.lua.ast;

public class Name implements SyntaxNode {

  private SyntaxNode parent;
  public String name;

  public Name (String name) {
    this.name = name;
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
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Name(\n");
    buffer.append("  "+tab+name);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Name]");
    return buffer.toString();
  }
}
