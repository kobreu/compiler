package edu.tum.lua.operator.logical;

import static edu.tum.lua.operator.logical.LogicalOperatorSupport.isTrue;
import edu.tum.lua.operator.BinaryOperator;

public final class AndOperator extends BinaryOperator {
	@Override
	public Object apply(Object op1, Object op2) {
		if (!isTrue(op1)) {
			return op1;
		}

		return op2;
	}
}
