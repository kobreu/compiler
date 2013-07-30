package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class PCall extends LuaFunctionNative {

	private static final LuaType[][] types = { null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("pcall", arguments, types);
		Object f = arguments.get(0);

		if (LuaType.FUNCTION != LuaType.getTypeOf(f)) {
			return Arrays.asList(false, (Object) ("attempt to call a " + LuaType.getTypeOf(f).toString() + " value"));
		}

		try {
			List<Object> result = new ArrayList<>();
			result.add(true);
			result.addAll(((LuaFunction) f).apply(arguments.subList(1, arguments.size())));
			return result;
		} catch (LuaRuntimeException ex) {
			return Arrays.asList(false, (Object) ex.getMessage());
		}
	}
}
