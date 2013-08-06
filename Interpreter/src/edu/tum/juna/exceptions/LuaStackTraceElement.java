package edu.tum.juna.exceptions;

import java.util.Collections;
import java.util.List;

import edu.tum.juna.ast.SyntaxNode;
import edu.tum.juna.parser.location.Location;

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
