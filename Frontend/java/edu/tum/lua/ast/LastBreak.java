/*
 * Generated by classgen, version 1.5
 * 30.07.13 16:47
 */
package edu.tum.lua.ast;

public class LastBreak extends LastStat {


  public LastBreak () {
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
    buffer.append("LastBreak(\n");
    buffer.append(tab);
    buffer.append(") [LastBreak]");
    return buffer.toString();
  }
}
