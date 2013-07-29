package edu.tum.lua.operator.list;

import edu.tum.lua.operator.UnaryOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class LengthOperator extends UnaryOperator {

	@Override
	public Object apply(Object op) {
		switch (LuaType.getTypeOf(op)) {
		case STRING:
			return new Double(((String) op).length());

		case TABLE:
			double value = 0;
			while (((LuaTable) op).get(value + 1) != null) {
				value++;
			}
			return value;

		default:
			LuaFunction handler = getHandler("__len", op);
			return handler.apply(op);
		}
	}

}
