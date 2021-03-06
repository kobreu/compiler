package edu.tum.juna.stdlib;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.List;

import edu.tum.juna.exceptions.LuaAssertionException;
import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

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
						&& LuaType.getTypeOf(arguments.get(1)) != LuaType.STRING) {
					throw new LuaBadArgumentException(2, "assert", "string", LuaType.getTypeOf(arguments.get(1))
							.toString());
				}
				message = arguments.get(1).toString();
			}

			throw new LuaAssertionException(message);
		}

		return arguments;
	}
}
