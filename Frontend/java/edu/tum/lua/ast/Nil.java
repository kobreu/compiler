/*
 * Generated by classgen, version 1.5
<<<<<<< HEAD
 * 23.07.13 15:35
=======
 * 24.07.13 15:58
>>>>>>> Intermediate
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
