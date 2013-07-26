/*
 * Generated by classgen, version 1.5
 * 26.07.13 16:53
 */
package edu.tum.lua.ast;

import java.util.Vector;
import java.util.Enumeration;

public class NameList implements SyntaxNode {

  private Vector items;
  private SyntaxNode parent;

  public NameList() {
    items = new Vector();
  }

  public NameList(Name anItem) {
    this();
    append(anItem);
  }

  public NameList append(Name anItem) {
    if (anItem == null) return this;
    anItem.setParent(this);
    items.addElement(anItem);
    return this;
  }

  public Enumeration elements() {
    return items.elements();
  }

  public Name elementAt(int index) {
    return (Name) items.elementAt(index);
  }

  public void setElementAt(Name item, int index) {
    item.setParent(this);
    items.setElementAt(item, index);
  }

  public void insertElementAt(Name item, int index) {
    item.setParent(this);
    items.insertElementAt(item, index);
  }

  public void removeElementAt(int index) {
    items.removeElementAt(index);
  }

  public int size() { return items.size(); }

  public boolean isEmpty() { return items.isEmpty(); }

  public boolean contains(Name item) {
    int size = items.size();
    for (int i = 0; i < size; i++)
      if ( item.equals(items.elementAt(i)) ) return true;
    return false;
  }

  public int indexOf(Name item) {
    return items.indexOf(item);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("NameList (\n");
    int size = items.size();
    for (int i = 0; i < size; i++) {
      buffer.append(((Name) items.elementAt(i)).toString("  "+tab));
      buffer.append("\n");
    }
    buffer.append(tab);
    buffer.append(") [NameList]");
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
