package edu.tum.juna.stdlib;

import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Error extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("error", arguments, expectedTypes);
		Object message = arguments.get(0);
		throw new LuaRuntimeException(message.toString());
	}

}
