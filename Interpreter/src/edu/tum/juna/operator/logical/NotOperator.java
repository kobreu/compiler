package edu.tum.juna.operator.logical;

import static edu.tum.juna.operator.logical.LogicalOperatorSupport.isTrue;
import edu.tum.juna.operator.UnaryOperator;

public final class NotOperator extends UnaryOperator {
	@Override
	public Object apply(Object op1) {
		if (isTrue(op1)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
