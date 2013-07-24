package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Rad extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		/* Convert degrees to radian */

		Preconditions.checkArguments("rad", arguments, expectedTypes);
		double degree = (double) arguments.get(0);
		double ret = Math.PI * (degree / 180);
		return Arrays.asList((Object) ret);
	}
}
