/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

public class Dots extends Exp {


  public Dots () {
  }

  @Override
public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
public void childrenAccept(Visitor visitor) {
  }

  @Override
public void traverseTopDown(Visitor visitor) {
    accept(visitor);
  }

  @Override
public void traverseBottomUp(Visitor visitor) {
    accept(visitor);
  }

  @Override
public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Dots(\n");
    buffer.append(tab);
    buffer.append(") [Dots]");
    return buffer.toString();
  }
}
