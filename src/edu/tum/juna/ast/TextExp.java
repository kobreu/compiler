/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class TextExp extends Exp {

  public String text;

  public TextExp (String text) {
    this.text = text;
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
    buffer.append("TextExp(\n");
    buffer.append("  "+tab+text);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TextExp]");
    return buffer.toString();
  }
}
