package edu.tum.juna.exceptions;

public class LuaBadArgumentException extends LuaRuntimeException {

	private static final long serialVersionUID = 7358323281432067832L;

	public LuaBadArgumentException(int pos, String functionName, String expected, String got) {
		super("bad argument #" + pos + " to '" + functionName + "' (" + expected + " expected, got " + got + ")");
	}

	public LuaBadArgumentException(int pos, String functionName, String message) {
		super("bad argument #" + pos + " to '" + functionName + "' (" + message + ")");
	}
}
