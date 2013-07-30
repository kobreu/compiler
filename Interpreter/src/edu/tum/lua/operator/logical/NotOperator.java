package edu.tum.lua.operator.logical;

import static edu.tum.lua.operator.logical.LogicalOperatorSupport.isTrue;
import edu.tum.lua.operator.UnaryOperator;

public final class NotOperator extends UnaryOperator {
	@Override
	public Object apply(Object op1) {
		if (isTrue(op1)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
