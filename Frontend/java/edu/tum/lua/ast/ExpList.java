/*
 * Generated by classgen, version 1.5
 * 23.07.13 15:35
 */
package edu.tum.lua.ast;

import java.util.Vector;
import java.util.Enumeration;

public class ExpList implements SyntaxNode {

  private Vector items;
  private SyntaxNode parent;

  public ExpList() {
    items = new Vector();
  }

  public ExpList(Exp anItem) {
    this();
    append(anItem);
  }

  public ExpList append(Exp anItem) {
    if (anItem == null) return this;
    anItem.setParent(this);
    items.addElement(anItem);
    return this;
  }

  public Enumeration elements() {
    return items.elements();
  }

  public Exp elementAt(int index) {
    return (Exp) items.elementAt(index);
  }

  public void setElementAt(Exp item, int index) {
    item.setParent(this);
    items.setElementAt(item, index);
  }

  public void insertElementAt(Exp item, int index) {
    item.setParent(this);
    items.insertElementAt(item, index);
  }

  public void removeElementAt(int index) {
    items.removeElementAt(index);
  }

  public int size() { return items.size(); }

  public boolean isEmpty() { return items.isEmpty(); }

  public boolean contains(Exp item) {
    int size = items.size();
    for (int i = 0; i < size; i++)
      if ( item.equals(items.elementAt(i)) ) return true;
    return false;
  }

  public int indexOf(Exp item) {
    return items.indexOf(item);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ExpList (\n");
    int size = items.size();
    for (int i = 0; i < size; i++) {
      buffer.append(((Exp) items.elementAt(i)).toString("  "+tab));
      buffer.append("\n");
    }
    buffer.append(tab);
    buffer.append(") [ExpList]");
    return buffer.toString();
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    for (int i = 0; i < size(); i++)
      if (elementAt(i) != null) elementAt(i).accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    this.accept(visitor);
    for (int i = 0; i < size(); i++)
      if (elementAt(i) != null) elementAt(i).traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    for (int i = 0; i < size(); i++)
      if (elementAt(i) != null) elementAt(i).traverseBottomUp(visitor);
    this.accept(visitor);
  }

}
