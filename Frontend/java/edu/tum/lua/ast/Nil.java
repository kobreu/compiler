/*
 * Generated by classgen, version 1.5
 * 30.07.13 14:49
 */
package edu.tum.lua.ast;

public class Nil extends Exp {


  public Nil () {
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

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Nil(\n");
    buffer.append(tab);
    buffer.append(") [Nil]");
    return buffer.toString();
  }
}
