package edu.tum.lua.operator.arithmetic;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.types.LuaFunction;

public final class UnmOperator extends ArithmeticOperator {
	public Object apply(Object op1) throws NoSuchMethodException {
		try {
			return -convert(op1);
		} catch (NumberFormatException | OperationNotSupportedException e) {
			LuaFunction handler = getHandler("__unm", op1);
			return handler.apply(op1).get(0);
		}
	}
}
