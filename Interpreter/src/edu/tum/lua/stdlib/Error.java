package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Error extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("error", arguments, expectedTypes);
		Object message = arguments.get(0);
		throw new LuaRuntimeException(message.toString());
	}

}
