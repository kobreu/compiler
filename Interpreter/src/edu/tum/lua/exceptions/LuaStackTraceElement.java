package edu.tum.lua.exceptions;

import java.util.Collections;
import java.util.List;

import location.Location;
import location.LocationTableFactory;
import edu.tum.lua.ast.SyntaxNode;

public class LuaStackTraceElement {

	public final String functionName;
	public final Location location;
	public final List<Object> args;

	public LuaStackTraceElement(Location l, String f, List<Object> a) {
		functionName = f;
		location = l;
		args = Collections.unmodifiableList(a);
	}

	public LuaStackTraceElement(SyntaxNode s, String f, List<Object> a) {
		functionName = f;
		location = LocationTableFactory.locationProvider().getLocation(s);
		args = Collections.unmodifiableList(a);
	}
}
