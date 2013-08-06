package edu.tum.juna.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunction;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaThread;
import edu.tum.juna.types.LuaType;

public class Create extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.FUNCTION } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.create", arguments, expectedTypes);
		return Arrays.asList((Object) new LuaThread((LuaFunction) arguments.get(0)));
	}

}
