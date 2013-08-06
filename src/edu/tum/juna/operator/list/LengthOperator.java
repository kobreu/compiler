package edu.tum.juna.operator.list;

import edu.tum.juna.operator.UnaryOperator;
import edu.tum.juna.types.LuaFunction;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

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
