package edu.tum.lua.operator.relational;

<<<<<<< HEAD
/**
 * The NotEqual operator is the exact opposite of the Equal operator
 */
public class NotEqOperator {
	private final static EqOperator eqOperator = new EqOperator();

	public boolean apply(Object o1, Object o2) {
=======
import edu.tum.lua.operator.BinaryOperator;

/**
 * The NotEqual operator is the exact opposite of the Equal operator
 */
public class NotEqOperator extends BinaryOperator {
	private final static EqOperator eqOperator = new EqOperator();

	@Override
	public Boolean apply(Object o1, Object o2) {
>>>>>>> Parser
		return !eqOperator.apply(o1, o2);
	}
}
