package edu.tum.lua.operator.arithmetic;

public final class PowOperator extends BinaryArithmeticOperator {

	@Override
	protected double apply(double op1, double op2) {
		return Math.pow(op1, op2);
	}

	@Override
	protected String handlerName() {
		return "__pow";
	}

}
