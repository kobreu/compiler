package edu.tum.juna.operator.arithmetic;

public final class MulOperator extends BinaryArithmeticOperator {

	@Override
	protected double apply(double op1, double op2) {
		return op1 * op2;
	}

	@Override
	protected String handlerName() {
		return "__mul";
	}

}
