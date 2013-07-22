package edu.tum.lua.operator.arithmetic;

public final class ModOperator extends BinaryArithmeticOperation {

	@Override
	protected double apply(double op1, double op2) {
		return op1 - Math.floor(op1 / op2) * op2;
	}

	@Override
	protected String handlerName() {
		return "__mod";
	}

}
