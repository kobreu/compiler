package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToString extends LuaFunctionNative {

	LuaType[][] expectedTypes = { null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("tostring", arguments, expectedTypes);

		List<Object> list = new LinkedList<Object>();
		Object o = arguments.get(0);

		switch (LuaType.getTypeOf(o)) {
		case STRING:
			list.add(o);
			return list;
		case BOOLEAN:
			list.add(Boolean.toString((boolean) o));
			return list;
		case NIL:
			list.add("nil");
			return list;
		case NUMBER:
			if (((Double) o).doubleValue() == new Double(((Double) o).intValue()).doubleValue())
				list.add(Integer.toString(((Double) o).intValue()));
			else
				list.add(((Double) o).toString());
			return list;
		case TABLE:
			list.add(o.toString());
			return list;
		case FUNCTION:
			list.add(o.toString());
			return list;
		default:
			throw new LuaRuntimeException("unknown Object");
		}
	}

	protected String handlerName() {
		return "__tostring";
	}
}
