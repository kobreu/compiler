package edu.tum.juna.operator.arithmetic;

import static edu.tum.juna.operator.arithmetic.ArithmeticOperatorSupport.convert;
import edu.tum.juna.exceptions.LuaArithmeticOperationNotSupportedException;
import edu.tum.juna.operator.UnaryOperator;
import edu.tum.juna.types.LuaFunction;

public final class UnmOperator extends UnaryOperator {

	@Override
	public Object apply(Object op1) {
		try {
			return -convert(op1);
		} catch (NumberFormatException | LuaArithmeticOperationNotSupportedException e) {
			LuaFunction handler = getHandler("__unm", op1);
			return handler.apply(op1).get(0);
		}
	}
}
