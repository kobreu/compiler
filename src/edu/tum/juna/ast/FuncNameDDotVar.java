/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class FuncNameDDotVar extends FuncName {

  public Name selffuncname;
  public Name funcname;

  public FuncNameDDotVar (Name selffuncname, Name funcname) {
    this.selffuncname = selffuncname;
    if (selffuncname != null) selffuncname.setParent(this);
    this.funcname = funcname;
    if (funcname != null) funcname.setParent(this);
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
    if (selffuncname != null) selffuncname.accept(visitor);
    if (funcname != null) funcname.accept(visitor);
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (selffuncname != null) selffuncname.traverseTopDown(visitor);
    if (funcname != null) funcname.traverseTopDown(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    if (selffuncname != null) selffuncname.traverseBottomUp(visitor);
    if (funcname != null) funcname.traverseBottomUp(visitor);
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncNameDDotVar(\n");
      if (selffuncname != null)
        buffer.append(selffuncname.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (funcname != null)
        buffer.append(funcname.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncNameDDotVar]");
    return buffer.toString();
  }
}
