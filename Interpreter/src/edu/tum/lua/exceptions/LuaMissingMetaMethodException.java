package edu.tum.lua.exceptions;

public class LuaMissingMetaMethodException extends LuaRuntimeException {

	private static final long serialVersionUID = -2051209813293069556L;

	public LuaMissingMetaMethodException() {
		super("missing metamethod");
	}

}
