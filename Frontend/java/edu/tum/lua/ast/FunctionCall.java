/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public abstract class FunctionCall extends SyntaxNode {

  private SyntaxNode parent;

  @Override
public SyntaxNode getParent() {
    return parent;
  }

  @Override
public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  @Override
public abstract void accept(Visitor visitor);
  @Override
public abstract void childrenAccept(Visitor visitor);
  @Override
public abstract void traverseTopDown(Visitor visitor);
  @Override
public abstract void traverseBottomUp(Visitor visitor);
  @Override
public String toString() {
    return toString("");
  }

  public abstract String toString(String tab);
}
