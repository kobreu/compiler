package edu.tum.lua.exceptions;

public class LuaInvalidKeyException extends LuaRuntimeException {

	private static final long serialVersionUID = 7054064368518675302L;

	public LuaInvalidKeyException(String name) {
		super("invalid key for " + name);
	}

}
