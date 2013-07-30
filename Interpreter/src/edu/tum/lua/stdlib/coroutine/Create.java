package edu.tum.lua.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaThread;
import edu.tum.lua.types.LuaType;

public class Create extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.FUNCTION } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.create", arguments, expectedTypes);
		return Arrays.asList((Object) new LuaThread((LuaFunction) arguments.get(0)));
	}

}
