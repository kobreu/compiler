package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Assert extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.BOOLEAN, LuaType.STRING } };
	final private String stdMessage = "assertion failed!";

	@Override
	public List<Object> apply(List<Object> arguments) {
		/*
		 * Usage: assert(boolean v [, String message])
		 */

		checkArguments("assert", arguments, expectedTypes);

		if ((Boolean) arguments.get(0) == false) {
			// Throw a LuaAssertionError

			String message = stdMessage;

			if (arguments.size() == 2) {
				message = (String) arguments.get(1);
			}

			throw new LuaRuntimeException(message);
		}

		return null;
	}
}
