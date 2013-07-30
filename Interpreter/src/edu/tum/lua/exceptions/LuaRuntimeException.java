package edu.tum.lua.exceptions;

import location.Location;

public class LuaRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1964316201991351991L;

	private final Location location;

	public LuaRuntimeException(String message) {
		super(message);
		location = null;
	}

	public Location getLocation() {
		return location;
	}
}
