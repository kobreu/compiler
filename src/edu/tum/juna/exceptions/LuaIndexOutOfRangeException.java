package edu.tum.juna.exceptions;

public class LuaIndexOutOfRangeException extends LuaRuntimeException {

	private static final long serialVersionUID = 8440611253622425084L;

	public LuaIndexOutOfRangeException(int position, String name) {
		super("bad argument #" + position + " to " + name + " (index out of range");
	}

	public LuaIndexOutOfRangeException(String name) {
		super("bad argument to " + name + " (value out of range");
	}
}
