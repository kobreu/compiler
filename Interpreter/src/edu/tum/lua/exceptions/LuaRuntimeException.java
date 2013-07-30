package edu.tum.lua.exceptions;

import java.util.Deque;
import java.util.LinkedList;

import location.Location;

public class LuaRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1964316201991351991L;

	private final Location location;

	Deque<LuaStackTraceElement> stacktrace;

	public LuaRuntimeException(String message, Location location) {
		super(message);
		this.location = location;
		stacktrace = new LinkedList<>();
	}

	public Location getLocation() {
		return location;
	}

	public void addLuaStackTraceElement(LuaStackTraceElement e) {
		stacktrace.addLast(e);
	}
}
