package edu.tum.lua.junit.operator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.operator.OperatorRegistery;
import edu.tum.lua.operator.arithmetic.AddOperator;

public class OperatorRegisteryTest {

	@Test
	public void test() {
		OperatorRegistery op = new OperatorRegistery();
		try {
			op.apply(20);
			fail();
		} catch (LuaRuntimeException e) {
		}
		try {
			assertTrue(op.apply(0) instanceof AddOperator);
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
