package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Log extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("log", arguments, expectedTypes);
		double ret = Math.log((double) arguments.get(0));
		return Arrays.asList((Object) ret);
	}

}
