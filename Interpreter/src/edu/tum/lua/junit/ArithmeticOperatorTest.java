package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.operator.arithmetic.AddOperator;
import edu.tum.lua.operator.arithmetic.BinaryArithmeticOperator;
import edu.tum.lua.operator.arithmetic.DivOperator;
import edu.tum.lua.operator.arithmetic.ModOperator;
import edu.tum.lua.operator.arithmetic.MulOperator;
import edu.tum.lua.operator.arithmetic.PowOperator;
import edu.tum.lua.operator.arithmetic.SubOperator;
import edu.tum.lua.operator.arithmetic.UnmOperator;
import edu.tum.lua.types.LuaTable;

public class ArithmeticOperatorTest {

	AddOperator op;
	Object[] invalidObjects;

	@Before
	public void setUp() throws Exception {
		op = new AddOperator();

		LuaTable table = new LuaTable();
		table.setMetatable(new LuaTable());
		invalidObjects = new Object[] { null, false, true, "abc", new LuaTable(), table };
	}

	@Test
	public void testApply() {
		double op1 = 10;
		double op2 = 3;

		testBinaryOperator(new AddOperator(), op1, op2, op1 + op2);
		testBinaryOperator(new DivOperator(), op1, op2, op1 / op2);
		testBinaryOperator(new ModOperator(), op1, op2, op1 % op2);
		testBinaryOperator(new MulOperator(), op1, op2, op1 * op2);
		testBinaryOperator(new PowOperator(), op1, op2, Math.pow(op1, op2));
		testBinaryOperator(new SubOperator(), op1, op2, op1 - op2);
		testUnaryOperator(new UnmOperator(), op1, -op1);
	}

	private void testUnaryOperator(UnmOperator op, double op1, double result) {
		try {
			assertEquals(result, op.apply(op1));
			assertEquals(result, op.apply(Double.toString(op1)));
		} catch (NoSuchMethodException e) {
			fail();
		}

		for (Object inOp : invalidObjects) {
			try {
				op.apply(inOp);
			} catch (NoSuchMethodException ex) {
				continue;
			}

			fail("Missing NoSuchMethodException exception");
		}
	}

	private void testBinaryOperator(BinaryArithmeticOperator op, double op1, double op2, double result) {
		try {
			assertEquals(result, op.apply(op1, op2));
			assertEquals(result, op.apply(Double.toString(op1), op2));
			assertEquals(result, op.apply(op1, Double.toString(op2)));
			assertEquals(result, op.apply(Double.toString(op1), Double.toString(op2)));
		} catch (NoSuchMethodException e) {
			fail();
		}

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
