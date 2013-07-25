package edu.tum.lua.stdlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Xpcall extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { null, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("xpcall", arguments, expectedTypes);
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
			Object err = arguments.get(1);
			if (LuaType.FUNCTION != LuaType.getTypeOf(err)) {
				return Arrays.asList(false, (Object) ("error in error handling"));
			}
			return Arrays.asList(false, ((LuaFunction) err).apply(ex.getMessage()));
		}

	}

}
