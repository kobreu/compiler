package edu.tum.juna.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Tan extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("tan", arguments, expectedTypes);
		double ret = Math.tan((double) arguments.get(0));
		return Arrays.asList((Object) ret);
	}

}
