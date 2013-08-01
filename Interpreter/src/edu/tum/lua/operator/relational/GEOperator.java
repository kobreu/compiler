package edu.tum.lua.operator.relational;

import edu.tum.lua.operator.BinaryOperator;

public class GEOperator extends BinaryOperator {
	/* Translate a >= b to b <= a */

	private final LEOperator leoperator;

	public GEOperator() {
		leoperator = new LEOperator();
	}

	@Override
	public Boolean apply(Object o1, Object o2) {
		return leoperator.apply(o2, o1);
	}

}
