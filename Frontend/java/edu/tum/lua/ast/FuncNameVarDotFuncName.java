/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public class FuncNameVarDotFuncName extends FuncName {

  public Name name;
  public FuncName funcnamelist;

  public FuncNameVarDotFuncName (Name name, FuncName funcnamelist) {
    this.name = name;
    if (name != null) name.setParent(this);
    this.funcnamelist = funcnamelist;
    if (funcnamelist != null) funcnamelist.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (name != null) name.accept(visitor);
    if (funcnamelist != null) funcnamelist.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (name != null) name.traverseTopDown(visitor);
    if (funcnamelist != null) funcnamelist.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (name != null) name.traverseBottomUp(visitor);
    if (funcnamelist != null) funcnamelist.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncNameVarDotFuncName(\n");
      if (name != null)
        buffer.append(name.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (funcnamelist != null)
        buffer.append(funcnamelist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncNameVarDotFuncName]");
    return buffer.toString();
  }
}
