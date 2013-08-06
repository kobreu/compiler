package edu.tum.juna.operator.logical;

import edu.tum.juna.types.LuaType;

public final class LogicalOperatorSupport {
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