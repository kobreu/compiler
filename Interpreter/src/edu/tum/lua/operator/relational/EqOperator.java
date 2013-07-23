package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.Operator;
import edu.tum.lua.operator.logical.LogicalOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class EqOperator extends Operator {

	public boolean apply(Object o1, Object o2) throws NoSuchMethodException {

		// If LuaType is different return false
		if (LuaType.getTypeOf(o1) != LuaType.getTypeOf(o2)) {
			return false;
		}

		// Compare two numbers
		if (LuaType.getTypeOf(o1) == LuaType.NUMBER && LuaType.getTypeOf(o2) == LuaType.NUMBER) {

			// TODO is comparison by reference or value?
			return o1 == o2;
		}

		// Compare two strings
		if (LuaType.getTypeOf(o1) == LuaType.STRING && LuaType.getTypeOf(o2) == LuaType.STRING) {

			int comparison = ((String) o1).compareTo((String) o2);

			if (comparison == 0) {
				return true;
			}

			return false;
		}

		// Compare tables using metamethod "eq"
		if (LuaType.getTypeOf(o1) == LuaType.TABLE && LuaType.getTypeOf(o2) == LuaType.TABLE) {

			LuaFunction handler = getHandler(handlerName(), o1, o2);
			return LogicalOperator.isTrue(handler.apply(o1, o2).get(0));

		}

		// Everything else is compared by reference
		// tables without "eq", functions. (, and userdata, threads)
		return o1 == o2;
	}

	protected String handlerName() {
		return "eq";
	}
}
