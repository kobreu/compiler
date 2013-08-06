package edu.tum.juna.operator;

public abstract class UnaryOperator extends Operator {

	public UnaryOperator() {
		super();
	}

	public abstract Object apply(Object op1);

	@Override
	public Object apply(Object... operands) {
		return apply(operands[0]);
	}

}