package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.BinaryOperator;
import edu.tum.lua.operator.logical.LogicalOperatorSupport;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class LTOperator extends BinaryOperator {
	/* Less Than operator: a < b */

	@Override
	public Boolean apply(Object o1, Object o2) {

		// Compare two numbers
		if (LuaType.getTypeOf(o1) == LuaType.NUMBER && LuaType.getTypeOf(o2) == LuaType.NUMBER) {
			return (Double) o1 < (Double) o2;
		}

		// Compare two strings
		if (LuaType.getTypeOf(o1) == LuaType.STRING && LuaType.getTypeOf(o2) == LuaType.STRING) {

			int comparison = ((String) o1).compareTo((String) o2);
			return (comparison < 0);
		}

		// For other objects, try to call the "le" metamethod
		LuaFunction handler;
		handler = getHandler(handlerName(), o1, o2);
		return LogicalOperatorSupport.isTrue(handler.apply(o1, o2).get(0));
	}

	public String handlerName() {
		return "__lt";
	}
}
