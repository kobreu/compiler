package edu.tum.lua.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.operator.arithmetic.AddOperator;
import edu.tum.lua.operator.arithmetic.BinaryArithmeticOperation;
import edu.tum.lua.operator.arithmetic.DivOperator;
import edu.tum.lua.operator.arithmetic.ModOperator;
import edu.tum.lua.operator.arithmetic.MulOperator;
import edu.tum.lua.operator.arithmetic.PowOperator;
import edu.tum.lua.types.LuaTable;

public class ArithmeticOperatorTest {

	AddOperator op;

	@Before
	public void setUp() throws Exception {
		op = new AddOperator();
	}

	@Test
	public void testApply() throws NoSuchMethodException {
		double op1 = 10;
		double op2 = 3;

		testBinaryOperator(new AddOperator(), op1, op2, op1 + op2);
		testBinaryOperator(new DivOperator(), op1, op2, op1 / op2);
		testBinaryOperator(new ModOperator(), op1, op2, op1 % op2);
		testBinaryOperator(new MulOperator(), op1, op2, op1 * op2);
		testBinaryOperator(new PowOperator(), op1, op2, Math.pow(op1, op2));
	}

	private void testBinaryOperator(BinaryArithmeticOperation op, double op1, double op2, double result) {
		try {
			assertEquals(result, op.apply(op1, op2));
			assertEquals(result, op.apply(Double.toString(op1), op2));
			assertEquals(result, op.apply(op1, Double.toString(op2)));
			assertEquals(result, op.apply(Double.toString(op1), Double.toString(op2)));
		} catch (NoSuchMethodException e) {
			fail();
		}

		LuaTable table = new LuaTable();
		table.setMetatable(new LuaTable());

		Object[] invalidObjects = { null, false, true, "abc", new LuaTable(), table };

		for (Object inOp1 : invalidObjects) {
			for (Object inOp2 : invalidObjects) {
				try {
					op.apply(inOp1, inOp2);
				} catch (NoSuchMethodException ex) {
					continue;
				}

				fail("Missing NoSuchMethodException exception");
			}
		}
	}

}
