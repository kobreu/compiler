package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.Operator;

public class GEOperator extends Operator {
	/* Translate a >= b to b <= a */

	private LEOperator leoperator;

	public boolean apply(Object o1, Object o2) {

		return leoperator.apply(o2, o1);

	}

	@Override
	public Object apply(Object... operands) {
		return apply(operands[0], operands[1]);
	}
}
