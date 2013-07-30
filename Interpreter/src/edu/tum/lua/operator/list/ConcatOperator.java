package edu.tum.lua.operator.list;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.operator.BinaryOperator;
import edu.tum.lua.types.LuaFunction;
<<<<<<< HEAD

public class ConcatOperator extends Operator {
=======
import edu.tum.lua.types.LuaType;

public class ConcatOperator extends BinaryOperator {
>>>>>>> Parser

	protected static String convert(Object object) throws OperationNotSupportedException {
		if (LuaType.getTypeOf(object) == LuaType.STRING) {
			return (String) object;
		} else if (LuaType.getTypeOf(object) == LuaType.NUMBER) {
			return ((Double) object).toString();
		}
		throw new OperationNotSupportedException();
	}

<<<<<<< HEAD
	public Object apply(Object op1, Object op2) throws NoSuchMethodException {
		try {
			return applyString(convert(op1), convert(op2));
		} catch (OperationNotSupportedException e) {
			LuaFunction handler = getHandler("concat", op1, op2);
=======
	@Override
	public Object apply(Object op1, Object op2) {
		try {
			return applyString(convert(op1), convert(op2));
		} catch (OperationNotSupportedException e) {
			LuaFunction handler = getHandler("__concat", op1, op2);
>>>>>>> Parser
			return handler.apply(op1, op2);
		}

	}

<<<<<<< HEAD
	protected String applyString(String op1, String op2) {
=======
	private String applyString(String op1, String op2) {
>>>>>>> Parser
		return op1.concat(op2);
	}
}
