/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:50
 */
package edu.tum.juna.ast;

public class Name extends SyntaxNode {

	private SyntaxNode parent;
	public String name;

	public Name(String name) {
		this.name = name;
	}

	@Override
	public SyntaxNode getParent() {
		return parent;
	}

	@Override
	public void setParent(SyntaxNode parent) {
		this.parent = parent;
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
	public String toString() {
		return toString("");
	}

	public String toString(String tab) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(tab);
		buffer.append("Name(\n");
		buffer.append("  " + tab + name);
		buffer.append("\n");
		buffer.append(tab);
		buffer.append(") [Name]");
		return buffer.toString();
	}
}
