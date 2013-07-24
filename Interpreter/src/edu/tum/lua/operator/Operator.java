package edu.tum.lua.operator;

import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public abstract class Operator {
	protected static LuaFunction getHandler(String event, Object... objects) throws NoSuchMethodException {
		for (Object object : objects) {
			if (object instanceof LuaTable) {
				LuaTable table = (LuaTable) ((LuaTable) object).getMetatable();

				if (table == null) {
					continue;
				}

				Object function = table.get(event);

				if (LuaType.getTypeOf(function) == LuaType.FUNCTION) {
					return table.getLuaFunction(event);
				}
			}
		}

		throw new NoSuchMethodException();
	}
}