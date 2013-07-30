package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Pow extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("pow", arguments, expectedTypes);
		double ret = Math.pow((double) arguments.get(0), (double) arguments.get(1));
		return Arrays.asList((Object) ret);
	}

}
