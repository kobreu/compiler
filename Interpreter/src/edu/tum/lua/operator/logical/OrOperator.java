package edu.tum.lua.operator.logical;

public final class OrOperator extends LogicalOperator {
	public Object apply(Object op1, Object op2) {
		if (isTrue(op1)) {
			return op1;
		}

		return op2;
	}
}
