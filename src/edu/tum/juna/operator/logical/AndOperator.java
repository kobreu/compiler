package edu.tum.juna.operator.logical;

import static edu.tum.juna.operator.logical.LogicalOperatorSupport.isTrue;
import edu.tum.juna.operator.BinaryOperator;

public final class AndOperator extends BinaryOperator {
	@Override
	public Object apply(Object op1, Object op2) {
		if (!isTrue(op1)) {
			return op1;
		}

		return op2;
	}
}
