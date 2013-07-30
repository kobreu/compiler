package edu.tum.lua.stdlib;

<<<<<<< HEAD
import java.util.List;

import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.types.LuaFunctionNative;

public class Assert extends LuaFunctionNative {

	final private String stdmessage = "assertion failed!";
=======
import static edu.tum.lua.Preconditions.checkArguments;

import java.util.List;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Assert extends LuaFunctionNative {

	LuaType[][] expectedTypes = { null };
	final private String stdMessage = "assertion failed!";
>>>>>>> Parser

	@Override
	public List<Object> apply(List<Object> arguments) {
		/*
		 * Usage: assert(boolean v [, String message])
		 */
<<<<<<< HEAD

		if (arguments.size() != 1 && arguments.size() != 2) {
			// Wrong number of arguments
			throw new IllegalArgumentException();
		}
		if (arguments.get(0).getClass() != Boolean.class
				|| arguments.get(1).getClass() == String.class) {
			// Wrong argument types
			throw new IllegalArgumentException();
		}

		if ((Boolean) arguments.get(0) == false) {
			// Throw a LuaAssertionError
			// TODO throw new ...Exception..Error..

			// Print message to stderr
			String message = stdmessage;

			if (arguments.size() == 2) {
				message = (String) arguments.get(1);
			}
			System.err.println(message);
		}

		return null;
=======
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
>>>>>>> Parser
	}
}
