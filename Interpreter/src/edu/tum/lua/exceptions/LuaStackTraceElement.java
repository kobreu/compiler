package edu.tum.lua.exceptions;

import java.util.Collections;
import java.util.List;

import location.Location;
import edu.tum.lua.ast.SyntaxNode;

public class LuaStackTraceElement {

	public final String functionName;
	public final Location location;
	public final List<Object> args;
	public final SyntaxNode node;

	public LuaStackTraceElement(SyntaxNode s, String f, List<Object> a) {
		functionName = f;
		node = s;
		location = s.getStart();
		args = Collections.unmodifiableList(a);
	}
}
