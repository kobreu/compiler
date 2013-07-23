package edu.tum.lua.operator.arithmetic;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class ArithmeticOperation {

	protected static double convert(Object object) throws OperationNotSupportedException {
		if (LuaType.getTypeOf(object) == LuaType.NUMBER) {
			return (Double) object;
		} else if (LuaType.getTypeOf(object) == LuaType.STRING) {
			return new Double((String) object);
		}

		throw new OperationNotSupportedException();
	}

	protected static LuaFunction getHandler(String event, Object... objects) throws NoSuchMethodException {
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

		throw new NoSuchMethodException();
	}
}