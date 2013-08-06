package edu.tum.juna.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

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
