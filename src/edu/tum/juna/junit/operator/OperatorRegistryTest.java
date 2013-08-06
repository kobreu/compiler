package edu.tum.juna.junit.operator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.tum.juna.operator.Operator;
import edu.tum.juna.operator.OperatorRegistry;
import edu.tum.juna.operator.arithmetic.AddOperator;

public class OperatorRegistryTest {

	@Test
	public void test() {
		try {
			@SuppressWarnings("unused")
			Operator op = OperatorRegistry.registry[20];
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		assertTrue(OperatorRegistry.registry[0] instanceof AddOperator);
	}
}
