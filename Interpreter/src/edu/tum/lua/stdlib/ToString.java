package edu.tum.lua.stdlib;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToString extends LuaFunctionNative {

	LuaType[][] expectedTypes = { null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("tostring", arguments, expectedTypes);
		return Collections.singletonList((Object) toString(arguments.get(0)));
	}

	public static String toString(Object object) {
		switch (LuaType.getTypeOf(object)) {
		case STRING:
			return (String) object;

		case BOOLEAN:
			return Boolean.toString((Boolean) object);

		case NIL:
			return "nil";

		case NUMBER:
			return Double.toString((Double) object).replaceAll("\\.0", "");

		default:
			return object.toString();
		}
	}

	protected String handlerName() {
		return "__tostring";
	}
}
