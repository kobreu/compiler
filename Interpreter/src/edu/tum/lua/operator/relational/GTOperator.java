package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.Operator;

public class GTOperator extends Operator {
	/*
	 * Greater Than operator: a > b. Translate a > b to b < a
	 */

	private LTOperator ltoperator;

	public boolean apply(Object o1, Object o2) {
		return ltoperator.apply(o2, o1);
	}

	@Override
	public Object apply(Object... operands) {
		return apply(operands[0], operands[1]);
	}

}
