package edu.tum.lua.stdlib;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Environment;
import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Getfenv extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		if (arguments.isEmpty())
			return Arrays.asList((Object) Environment.getGlobalEnvironment());
		Object f = arguments.get(0);
		if (LuaType.getTypeOf(f) == LuaType.NUMBER)
			throw new LuaRuntimeException("not supported in this version");
		if (LuaType.getTypeOf(f) != LuaType.FUNCTION)
			throw new LuaBadArgumentException(1, "getfenv", "number", LuaType.getTypeOf(f).toString());
		if (f instanceof LuaFunctionNative)
			return Arrays.asList((Object) Environment.getGlobalEnvironment());
		return Arrays.asList((Object) ((LuaFunctionInterpreted) f).getGlobalEnvironment());
	}

}
