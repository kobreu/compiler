/*
 * Generated by classgen, version 1.5
 * 30.07.13 12:51
 */
package edu.tum.lua.ast;

public class FuncNameVar extends FuncName {

  public Name name;

  public FuncNameVar (Name name) {
    this.name = name;
    if (name != null) name.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (name != null) name.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (name != null) name.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (name != null) name.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncNameVar(\n");
      if (name != null)
        buffer.append(name.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncNameVar]");
    return buffer.toString();
  }
}
