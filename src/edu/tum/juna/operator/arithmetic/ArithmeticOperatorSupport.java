package edu.tum.juna.operator.arithmetic;

import edu.tum.juna.exceptions.LuaArithmeticOperationNotSupportedException;
import edu.tum.juna.types.LuaType;

public final class ArithmeticOperatorSupport {

	protected static double convert(Object object) {
		if (LuaType.getTypeOf(object) == LuaType.NUMBER) {
			return (Double) object;
		} else if (LuaType.getTypeOf(object) == LuaType.STRING) {
			return new Double((String) object);
		}

		throw new LuaArithmeticOperationNotSupportedException(LuaType.getTypeOf(object).toString());
	}

}