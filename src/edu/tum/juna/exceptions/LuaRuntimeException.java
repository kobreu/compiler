package edu.tum.juna.exceptions;

import java.util.Deque;
import java.util.LinkedList;

import edu.tum.juna.ast.SyntaxNode;
import edu.tum.juna.parser.location.Location;

public class LuaRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1964316201991351991L;

	private SyntaxNode node;

	Deque<LuaStackTraceElement> stacktrace;

	public LuaRuntimeException(String message) {
		super(message);
		stacktrace = new LinkedList<>();
	}

	public LuaRuntimeException(SyntaxNode node, String m) {
		this(m);
		this.node = node;
	}

	public Location getLocation() {
		return node.getStart();
	}

	public void addLuaStackTraceElement(LuaStackTraceElement e) {
		stacktrace.addLast(e);
	}

	public void offerSyntaxNode(SyntaxNode n) {
		if (node == null) {
			node = n;
		}
	}

	public SyntaxNode getSyntaxNode() {
		return node;
	}
}
