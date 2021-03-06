package edu.tum.juna.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Min extends LuaFunctionNative {

	// TODO the expected types are not right
	// min(v ..) they all need to be numbers
	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("min", arguments, expectedTypes);
		double min = (double) arguments.get(0);
		for (Object argument : arguments) {
			if ((double) argument < min) {
				min = (double) argument;
			}
		}

		return Arrays.asList((Object) min);
	}
}
