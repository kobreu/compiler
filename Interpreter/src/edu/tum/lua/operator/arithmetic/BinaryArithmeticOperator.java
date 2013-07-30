package edu.tum.lua.operator.arithmetic;

import static edu.tum.lua.operator.arithmetic.ArithmeticOperatorSupport.convert;
import edu.tum.lua.exceptions.LuaArithmeticOperationNotSupportedException;
import edu.tum.lua.operator.BinaryOperator;
import edu.tum.lua.types.LuaFunction;

public abstract class BinaryArithmeticOperator extends BinaryOperator {

	@Override
	public Object apply(Object op1, Object op2) {
		try {
			return apply(convert(op1), convert(op2));
		} catch (NumberFormatException | LuaArithmeticOperationNotSupportedException e) {
			LuaFunction handler = getHandler(handlerName(), op1, op2);
			return handler.apply(op1, op2).get(0);
		}
	}

	protected abstract double apply(double op1, double op2);

	protected abstract String handlerName();

}