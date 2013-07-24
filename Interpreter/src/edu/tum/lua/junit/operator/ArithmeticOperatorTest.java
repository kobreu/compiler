package edu.tum.lua.junit.operator;

import static edu.tum.lua.Preconditions.checkArguments;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

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
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

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

	protected static class FracAdd extends LuaFunctionNative {
		LuaType[][] types = { { LuaType.TABLE }, { LuaType.TABLE } };

		@Override
		public List<Object> apply(List<Object> arguments) {
			checkArguments("frac_add", arguments, types);
			LuaTable frac1 = (LuaTable) arguments.get(0);
			LuaTable frac2 = (LuaTable) arguments.get(1);
			LuaTable result = new LuaTable();
			result.set("a", frac1.getNumber("a") + frac2.getNumber("a"));
			result.set("b", frac1.get("b"));
			return Collections.singletonList((Object) result);
		}
	}

	@Test
	public void testApplyMeta() throws NoSuchMethodException {
		LuaTable frac1 = new LuaTable();
		LuaTable frac2 = new LuaTable();
		LuaTable meta = new LuaTable();

		frac1.set("a", 2.0);
		frac1.set("b", 3.0);

		frac2.set("a", 1.0);
		frac2.set("b", 3.0);

		meta.set("__add", new FracAdd());
		frac2.setMetatable(meta);

		AddOperator add = new AddOperator();
		LuaTable result = (LuaTable) add.apply(frac1, frac2);

		assertEquals((Object) 3.0, (Double) result.getNumber("a"));
		assertEquals((Object) 3.0, (Double) result.getNumber("b"));
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
