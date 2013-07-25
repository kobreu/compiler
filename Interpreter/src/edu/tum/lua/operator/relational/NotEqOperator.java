package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.Operator;

/**
 * The NotEqual operator is the exact opposite of the Equal operator
 */
public class NotEqOperator extends Operator {
	private final static EqOperator eqOperator = new EqOperator();

	public boolean apply(Object o1, Object o2) {
		return !eqOperator.apply(o1, o2);
	}

	@Override
	public Object apply(Object... operands) {
		return apply(operands[0], operands[1]);
	}
}
