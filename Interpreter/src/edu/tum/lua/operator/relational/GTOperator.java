package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.Operator;

public class GTOperator extends Operator {
	/*
	 * Greater Than operator: a > b. Translate a > b to b < a
	 */

	private LTOperator ltoperator;

	public boolean apply(Object o1, Object o2) throws NoSuchMethodException {

		return ltoperator.apply(o2, o1);
	}

}
