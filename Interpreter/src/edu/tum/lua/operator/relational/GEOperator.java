package edu.tum.lua.operator.relational;

<<<<<<< HEAD
import edu.tum.lua.operator.Operator;

public class GEOperator extends Operator {
	/* Translate a >= b to b <= a */

	private LEOperator leoperator;
	
	public boolean apply(Object o1, Object o2) throws NoSuchMethodException {
		
		return leoperator.apply(o2, o1);
		
	}
	
=======
import edu.tum.lua.operator.BinaryOperator;

public class GEOperator extends BinaryOperator {
	/* Translate a >= b to b <= a */

	private LEOperator leoperator;

	@Override
	public Boolean apply(Object o1, Object o2) {
		return leoperator.apply(o2, o1);
	}

>>>>>>> Parser
}
