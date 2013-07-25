package edu.tum.lua.operator.logical;

import edu.tum.lua.operator.Operator;
import edu.tum.lua.types.LuaType;

public abstract class LogicalOperator extends Operator {
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