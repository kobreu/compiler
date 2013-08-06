package edu.tum.juna.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Pow extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("pow", arguments, expectedTypes);
		double ret = Math.pow((double) arguments.get(0), (double) arguments.get(1));
		return Arrays.asList((Object) ret);
	}

}
