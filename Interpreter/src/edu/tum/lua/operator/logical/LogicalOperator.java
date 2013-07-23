package edu.tum.lua.operator.logical;

import edu.tum.lua.types.LuaType;

public abstract class LogicalOperator {
	protected static boolean isTrue(Object object) {
		if (LuaType.getTypeOf(object) == LuaType.NIL) {
			return false;
		}

		if (LuaType.getTypeOf(object) != LuaType.BOOLEAN) {
			return true;
		}

		return (Boolean) object;
	}
}