/*
 * Generated by classgen, version 1.5
<<<<<<< HEAD
 * 23.07.13 15:35
=======
 * 24.07.13 15:58
>>>>>>> Intermediate
 */
package edu.tum.lua.ast;

public abstract class Var implements SyntaxNode {

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
