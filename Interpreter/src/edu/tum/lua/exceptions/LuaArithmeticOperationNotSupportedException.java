package edu.tum.lua.exceptions;

public class LuaArithmeticOperationNotSupportedException extends LuaRuntimeException {

	private static final long serialVersionUID = 1L;

	public LuaArithmeticOperationNotSupportedException(String type) {
		super("attempt to perform arithmetic on a " + type + " value");
	}
}
