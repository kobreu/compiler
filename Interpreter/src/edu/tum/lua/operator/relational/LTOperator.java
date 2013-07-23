package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.Operator;
import edu.tum.lua.operator.logical.LogicalOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class LTOperator extends Operator {
	/* Less Than operator: a < b */

	
	public boolean apply(Object o1, Object o2) throws NoSuchMethodException {
		
		// Compare two numbers
		if(LuaType.getTypeOf(o1) == LuaType.NUMBER &&
			LuaType.getTypeOf(o2) == LuaType.NUMBER) {

			return (Double) o1 < (Double) o2;
		}
		
		// Compare two strings
		if(LuaType.getTypeOf(o1) == LuaType.STRING &&
			LuaType.getTypeOf(o2) == LuaType.STRING) {
			
			int comparison = ((String) o1).compareTo((String) o2);
			if (comparison < 0) {
				return true;
			} 
			return false;
			
		}

		// For other objects, try to call the "le" metamethod
		LuaFunction handler;
		handler = getHandler(handlerName(), o1, o2);
		return LogicalOperator.isTrue(handler.apply(o1, o2).get(0));
		
	}
	
	public String handlerName() {
		return "lt";
	}
}
