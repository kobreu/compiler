package edu.tum.lua.exceptions;


public class LuaNotSupportedException extends LuaRuntimeException {

	private static final long serialVersionUID = 7298709350861771445L;

	public LuaNotSupportedException() {
		super("not supported in this version");
	}

}
