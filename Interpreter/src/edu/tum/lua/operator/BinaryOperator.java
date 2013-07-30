package edu.tum.lua.operator;


public abstract class BinaryOperator extends Operator {

	public BinaryOperator() {
		super();
	}

	public abstract Object apply(Object op1, Object op2);

	@Override
	public Object apply(Object... operands) {
		return apply(operands[0], operands[1]);
	}

}