package edu.tum.juna.operator.list;

import edu.tum.juna.exceptions.LuaArithmeticOperationNotSupportedException;
import edu.tum.juna.operator.BinaryOperator;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.types.LuaFunction;
import edu.tum.juna.types.LuaType;

public class ConcatOperator extends BinaryOperator {

	protected static String convert(Object object) {
		if (LuaType.getTypeOf(object) == LuaType.STRING) {
			return (String) object;
		} else if (LuaType.getTypeOf(object) == LuaType.NUMBER) {
			return ToString.toString(object);
		}

		throw new LuaArithmeticOperationNotSupportedException(LuaType.getTypeOf(object).toString());
	}

	@Override
	public Object apply(Object op1, Object op2) {
		try {
			return applyString(convert(op1), convert(op2));
		} catch (LuaArithmeticOperationNotSupportedException e) {
			LuaFunction handler = getHandler("__concat", op1, op2);
			return handler.apply(op1, op2);
		}

	}

	private String applyString(String op1, String op2) {
		return op1.concat(op2);
	}
}
