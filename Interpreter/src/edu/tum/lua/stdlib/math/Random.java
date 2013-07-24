package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Random extends LuaFunctionNative {

	LuaType[][] expectedTypes = { {} };

	@Override
	public List<Object> apply(List<Object> arguments) {

		Preconditions.checkArguments("random", arguments, expectedTypes);

		if (arguments.size() == 0) {
			// Random Real value from [0..1]
			double ret = Math.random();
			return Arrays.asList((Object) ret);
		} else if (arguments.size() == 1) {
			// Random Integer from [1..m]
			// TODO
		} else if (arguments.size() == 2) {
			// Random Integer from [m..n]
			// TODO
		}

		return null;
	}
}
