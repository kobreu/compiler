package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Sqrt extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("sqrt", arguments, expectedTypes);
		double ret = Math.sqrt((double) arguments.get(0));
		return Arrays.asList((Object) ret);
	}

}
