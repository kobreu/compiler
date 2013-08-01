package edu.tum.lua.exceptions;

import java.util.Deque;
import java.util.LinkedList;

import edu.tum.lua.ast.SyntaxNode;
import location.Location;

public class LuaRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1964316201991351991L;

	private Location location;

	Deque<LuaStackTraceElement> stacktrace;

	public LuaRuntimeException(String message) {
		super(message);
		this.location = null;
		stacktrace = new LinkedList<>();
	}

	public LuaRuntimeException(SyntaxNode node, String m) {
		this(m);
		setLocation(node);
	}

	public Location getLocation() {
		return location;
	}

	public void addLuaStackTraceElement(LuaStackTraceElement e) {
		stacktrace.addLast(e);
	}

	public void setLocation(Location l) {
		location = l;
	}

	public void setLocation(SyntaxNode n) {
		location = n.getMarker();
	}
}
