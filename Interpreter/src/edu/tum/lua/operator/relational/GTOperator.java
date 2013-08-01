package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.BinaryOperator;

public class GTOperator extends BinaryOperator {
	/*
	 * Greater Than operator: a > b. Translate a > b to b < a
	 */

	private final LTOperator ltoperator;

	public GTOperator() {
		ltoperator = new LTOperator();
	}

	@Override
	public Boolean apply(Object o1, Object o2) {
		return ltoperator.apply(o2, o1);
	}
}
