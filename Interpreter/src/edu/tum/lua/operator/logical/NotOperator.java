package edu.tum.lua.operator.logical;

public final class NotOperator extends LogicalOperator {
	public Object apply(Object op1) {
		if (isTrue(op1)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	public Object apply(Object... operands) {
		return apply(operands[0]);
	}
}
