package edu.tum.lua.operator.relational;

<<<<<<< HEAD
import edu.tum.lua.operator.Operator;
import edu.tum.lua.operator.logical.LogicalOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class LEOperator extends Operator {

	public boolean apply(Object o1, Object o2) throws NoSuchMethodException {

		// Compare two numbers
		if (LuaType.getTypeOf(o1) == LuaType.NUMBER
				&& LuaType.getTypeOf(o2) == LuaType.NUMBER) {
=======
import edu.tum.lua.operator.BinaryOperator;
import edu.tum.lua.operator.logical.LogicalOperatorSupport;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class LEOperator extends BinaryOperator {

	@Override
	public Boolean apply(Object o1, Object o2) {

		// Compare two numbers
		if (LuaType.getTypeOf(o1) == LuaType.NUMBER && LuaType.getTypeOf(o2) == LuaType.NUMBER) {
>>>>>>> Parser

			return (Double) o1 <= (Double) o2;
		}

		// Compare two strings
<<<<<<< HEAD
		if (LuaType.getTypeOf(o1) == LuaType.STRING
				&& LuaType.getTypeOf(o2) == LuaType.STRING) {
=======
		if (LuaType.getTypeOf(o1) == LuaType.STRING && LuaType.getTypeOf(o2) == LuaType.STRING) {
>>>>>>> Parser

			int comparison = ((String) o1).compareTo((String) o2);
			if (comparison <= 0) {
				return true;
			}
			return false;

		}

		// For other objects, try to call the "le" metamethod
		LuaFunction handler;
		handler = getHandler(handlerName(), o1, o2);
<<<<<<< HEAD
		return LogicalOperator.isTrue(handler.apply(o1, o2).get(0));
	}

	public String handlerName() {
		return "le";
	}

=======
		return LogicalOperatorSupport.isTrue(handler.apply(o1, o2).get(0));
	}

	public String handlerName() {
		return "__le";
	}
>>>>>>> Parser
}
