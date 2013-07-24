/*
 * Generated by classgen, version 1.5
 * 23.07.13 15:35
 */
package edu.tum.lua.ast;

public class LocalFuncDef extends Stat {

  public String ident;
  public FuncBody body;

  public LocalFuncDef (String ident, FuncBody body) {
    this.ident = ident;
    this.body = body;
    if (body != null) body.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (body != null) body.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (body != null) body.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (body != null) body.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("LocalFuncDef(\n");
    buffer.append("  "+tab+ident);
    buffer.append("\n");
      if (body != null)
        buffer.append(body.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [LocalFuncDef]");
    return buffer.toString();
  }
}