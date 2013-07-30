package edu.tum.lua.operator.relational;

<<<<<<< HEAD
import edu.tum.lua.operator.Operator;

public class GTOperator extends Operator {
=======
import edu.tum.lua.operator.BinaryOperator;

public class GTOperator extends BinaryOperator {
>>>>>>> Parser
	/*
	 * Greater Than operator: a > b. Translate a > b to b < a
	 */

	private LTOperator ltoperator;

<<<<<<< HEAD
	public boolean apply(Object o1, Object o2) throws NoSuchMethodException {

		return ltoperator.apply(o2, o1);
	}

=======
	@Override
	public Boolean apply(Object o1, Object o2) {
		return ltoperator.apply(o2, o1);
	}
>>>>>>> Parser
}
