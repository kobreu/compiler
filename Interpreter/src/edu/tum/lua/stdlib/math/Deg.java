package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Deg extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		/* Convert radian to degrees */

		Preconditions.checkArguments("deg", arguments, expectedTypes);
		double radians = (double) arguments.get(0);
		double ret = 180 * (radians / Math.PI);
		return Arrays.asList((Object) ret);
	}
}
