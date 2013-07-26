package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.BinaryOperator;

/**
 * The NotEqual operator is the exact opposite of the Equal operator
 */
public class NotEqOperator extends BinaryOperator {
	private final static EqOperator eqOperator = new EqOperator();

	@Override
	public Boolean apply(Object o1, Object o2) {
		return !eqOperator.apply(o1, o2);
	}
}
