package edu.tum.juna.stdlib.os;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Difftime extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.NUMBER }, { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("os.difftime", arguments, expectedTypes);
		double result = ((double) arguments.get(0)) - ((double) arguments.get(1));
		return Arrays.asList((Object) result);
	}

}
