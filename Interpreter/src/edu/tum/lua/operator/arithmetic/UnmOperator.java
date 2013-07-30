package edu.tum.lua.operator.arithmetic;

import static edu.tum.lua.operator.arithmetic.ArithmeticOperatorSupport.convert;
import edu.tum.lua.exceptions.LuaArithmeticOperationNotSupportedException;
import edu.tum.lua.operator.UnaryOperator;
import edu.tum.lua.types.LuaFunction;

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
