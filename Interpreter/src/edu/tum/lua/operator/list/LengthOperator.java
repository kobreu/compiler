package edu.tum.lua.operator.list;

import edu.tum.lua.operator.UnaryOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class LengthOperator extends UnaryOperator {

	@Override
	public Object apply(Object op) {
		if (LuaType.getTypeOf(op) == LuaType.STRING) {
			return op.toString().length();
		} else if (LuaType.getTypeOf(op) == LuaType.TABLE) {
			return applyTable((LuaTable) op);
		} else {
			LuaFunction handler = getHandler("__len", op);
			return handler.apply(op);
		}
	}

	private Object applyTable(LuaTable op) {
		double value = 0;
		while (op.get(value + 1) != null) {
			value++;
		}
		return value;
	}

}
