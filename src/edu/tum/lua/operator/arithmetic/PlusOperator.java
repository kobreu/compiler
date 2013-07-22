package edu.tum.lua.operator.arithmetic;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class PlusOperator {

	public Object apply(Object op1, Object op2) throws NoSuchMethodException {
		try {
			return convert(op1) + convert(op2);
		} catch (OperationNotSupportedException e) {
			LuaFunction handler = getHandler("__add", op1, op2);
			return handler.apply(op1, op2).get(0);
		}
	}

	private double convert(Object object) throws OperationNotSupportedException {
		if (LuaType.getTypeOf(object) == LuaType.NUMBER) {
			return (Double) object;
		} else if (LuaType.getTypeOf(object) == LuaType.STRING) {
			return new Double((String) object);
		}

		throw new OperationNotSupportedException();
	}

	private LuaFunction getHandler(String handler, Object... objects) throws NoSuchMethodException {
		for (Object object : objects) {
			if (object instanceof LuaTable) {
				return ((LuaTable) object).getMetatable().getLuaFunction(handler);
			}
		}

		throw new NoSuchMethodException();
	}
}