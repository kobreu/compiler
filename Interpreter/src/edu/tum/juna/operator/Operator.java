package edu.tum.juna.operator;

import edu.tum.juna.exceptions.LuaMissingMetaMethodException;
import edu.tum.juna.types.LuaFunction;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public abstract class Operator {
	protected static LuaFunction getHandler(String event, Object... objects) {
		for (Object object : objects) {
			if (object instanceof LuaTable) {
				LuaTable table = ((LuaTable) object).getMetatable();

				if (table == null) {
					continue;
				}

				Object function = table.get(event);

				if (LuaType.getTypeOf(function) == LuaType.FUNCTION) {
					return table.getLuaFunction(event);
				}
			}
		}

		throw new LuaMissingMetaMethodException();
	}

	public abstract Object apply(Object... operands);
}