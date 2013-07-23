package edu.tum.lua.operator.arithmetic;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.types.LuaFunction;

public abstract class BinaryArithmeticOperation extends ArithmeticOperation {

	public Object apply(Object op1, Object op2) throws NoSuchMethodException {
		try {
			return apply(convert(op1), convert(op2));
		} catch (OperationNotSupportedException e) {
			LuaFunction handler = getHandler(handlerName(), op1, op2);
			return handler.apply(op1, op2).get(0);
		}
	}

	protected abstract double apply(double op1, double op2);

	protected abstract String handlerName();

}