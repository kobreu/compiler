package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;

public class NotImplementedFunction extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		throw new LuaRuntimeException("Not yet implemented");
	}
}
