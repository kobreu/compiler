package edu.tum.lua.operator.arithmetic;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.operator.Operator;
import edu.tum.lua.types.LuaType;

public class ArithmeticOperator extends Operator {

	protected static double convert(Object object) throws OperationNotSupportedException {
		if (LuaType.getTypeOf(object) == LuaType.NUMBER) {
			return (Double) object;
		} else if (LuaType.getTypeOf(object) == LuaType.STRING) {
			return new Double((String) object);
		}

		throw new OperationNotSupportedException();
	}
}