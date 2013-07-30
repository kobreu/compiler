package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.List;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Assert extends LuaFunctionNative {

	LuaType[][] expectedTypes = { null };
	final private String stdMessage = "assertion failed!";

	@Override
	public List<Object> apply(List<Object> arguments) {
		/*
		 * Usage: assert(boolean v [, String message])
		 */
		checkArguments("assert", arguments, expectedTypes);

		if (arguments.get(0) == null
				|| (LuaType.getTypeOf(arguments.get(0)) == LuaType.BOOLEAN && !((boolean) arguments.get(0)))) {
			// Throw a LuaAssertionError

			String message = stdMessage;

			if (arguments.size() > 1) {
				if (LuaType.getTypeOf(arguments.get(1)) != LuaType.NUMBER
						&& LuaType.getTypeOf(arguments.get(1)) != LuaType.STRING)
					throw new LuaBadArgumentException(2, "assert", "string", LuaType.getTypeOf(arguments.get(1))
							.toString());
				message = arguments.get(1).toString();
			}

			throw new LuaRuntimeException(message);
		}

		return arguments;
	}
}
