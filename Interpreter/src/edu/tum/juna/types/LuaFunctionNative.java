package edu.tum.juna.types;

import java.util.Arrays;
import java.util.List;

public abstract class LuaFunctionNative implements LuaFunction {

	@Override
	public abstract List<Object> apply(List<Object> arguments);

	@Override
	public List<Object> apply(Object... arguments) {
		if (arguments.length == 1 && arguments[0] instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<Object> args = (List<Object>) arguments[0];
			return apply(args);
		}

		return apply(Arrays.asList(arguments));
	}
}
