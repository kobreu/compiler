package edu.tum.lua.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Random extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { null, LuaType.NUMBER }, { null, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {

		Preconditions.checkArguments("random", arguments, expectedTypes);

		if (arguments.size() == 0) {
			// Random Real value from [0..1]
			double ret = Math.random();
			return Arrays.asList((Object) ret);
		}

		int min = 0;
		int max = 0;

		if (arguments.size() == 1) {

			// Random Integer from [1..m]
			min = 1;
			max = (int) Math.round((double) arguments.get(0));

			if (max <= 0) {
				throw new LuaBadArgumentException(1, "random", "interval is empty");
			}

		} else if (arguments.size() >= 2) {

			// Random Integer from [m..n]
			min = (int) Math.round((double) arguments.get(0));
			max = (int) Math.round((double) arguments.get(1));

			if (max < min) {
				throw new LuaBadArgumentException(2, "random", "interval is empty");
			}

		}

		// Random Integer from [min..max]
		double ret = min + (int) (Math.random() * ((max - min) + 1));
		return Arrays.asList((Object) ret);
	}
}
