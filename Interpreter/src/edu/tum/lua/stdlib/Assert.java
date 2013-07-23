package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.types.LuaFunctionNative;

public class Assert extends LuaFunctionNative {

	final private String stdmessage = "assertion failed!";

	@Override
	public List<Object> apply(List<Object> arguments) {
		/*
		 * Usage: assert(boolean v [, String message])
		 */

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
	}
}
