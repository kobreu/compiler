/*
 * Generated by classgen, version 1.5
 * 30.07.13 14:49
 */
package edu.tum.lua.ast;

public class NumberExp extends Exp {

  public double number;

  public NumberExp (double number) {
    this.number = number;
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
    buffer.append("NumberExp(\n");
    buffer.append("  "+tab+number);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [NumberExp]");
    return buffer.toString();
  }
}
