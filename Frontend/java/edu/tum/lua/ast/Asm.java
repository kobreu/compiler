/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.lua.ast;

import location.Location;

public class Asm extends Stat {

  public VarList varlist;
  public ExpList explist;

  public Asm (VarList varlist, ExpList explist) {
    this.varlist = varlist;
    if (varlist != null) varlist.setParent(this);
    this.explist = explist;
    if (explist != null) explist.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
  
  	@Override
	public Location getStart() {
		return varlist.getStart();
	}
  	
  	@Override
  		public Location getEnd() {
  			return explist.getEnd();
  		}
  	

  public void childrenAccept(Visitor visitor) {
    if (varlist != null) varlist.accept(visitor);
    if (explist != null) explist.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (varlist != null) varlist.traverseTopDown(visitor);
    if (explist != null) explist.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (varlist != null) varlist.traverseBottomUp(visitor);
    if (explist != null) explist.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Asm(\n");
      if (varlist != null)
        buffer.append(varlist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (explist != null)
        buffer.append(explist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Asm]");
    return buffer.toString();
  }

}