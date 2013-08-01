/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public abstract class Stat extends SyntaxNode {

  private SyntaxNode parent;

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public abstract void accept(Visitor visitor);
  public abstract void childrenAccept(Visitor visitor);
  public abstract void traverseTopDown(Visitor visitor);
  public abstract void traverseBottomUp(Visitor visitor);
  public String toString() {
    return toString("");
  }

  public abstract String toString(String tab);
}