package edu.tum.lua.operator.logical;

import edu.tum.lua.types.LuaType;

<<<<<<< HEAD:Interpreter/src/edu/tum/lua/operator/logical/LogicalOperator.java
public abstract class LogicalOperator {
=======
public final class LogicalOperatorSupport {
>>>>>>> Parser:Interpreter/src/edu/tum/lua/operator/logical/LogicalOperatorSupport.java
	public static boolean isTrue(Object object) {
		if (LuaType.getTypeOf(object) == LuaType.NIL) {
			return false;
		}

		if (LuaType.getTypeOf(object) != LuaType.BOOLEAN) {
			return true;
		}

		return (Boolean) object;
	}
}