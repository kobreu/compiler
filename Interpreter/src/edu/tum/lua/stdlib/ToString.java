package edu.tum.lua.stdlib;

<<<<<<< HEAD
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
=======
import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
>>>>>>> Parser
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToString extends LuaFunctionNative {

<<<<<<< HEAD
	@Override
	public List<Object> apply(List<Object> arguments) {
		if (arguments.isEmpty()) {
			throw new LuaRuntimeException("empty input");
		}
		
		List<Object> list = new LinkedList<Object>();
		Object o = arguments.get(0);
		
		if (LuaType.getTypeOf(o) == LuaType.STRING) {
			list.add(o);
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.BOOLEAN) {
			list.add( Boolean.toString((boolean) o) );
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.NIL) {
			list.add("nil");
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			list.add(Double.toString((double) o));
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.TABLE || LuaType.getTypeOf(o) == LuaType.FUNCTION) {
			list.add(o.toString());
			return list;
		}
		
		throw new LuaRuntimeException("unknown Object");
	}

=======
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
>>>>>>> Parser
}
