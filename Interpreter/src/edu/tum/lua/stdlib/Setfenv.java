package edu.tum.lua.stdlib;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Environment;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Setfenv extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.FUNCTION, LuaType.NUMBER }, { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("setfenv", arguments, expectedTypes);
		Object f = arguments.get(0);
		if (LuaType.getTypeOf(f) == LuaType.NUMBER)
			throw new LuaRuntimeException("not supported in this version");
		if (f instanceof LuaFunctionNative)
			throw new LuaRuntimeException("not supported in this version");
		((LuaFunctionInterpreted) f).setGlobalEnvironment((Environment) arguments.get(1));
		return Arrays.asList(f);
	}

}
