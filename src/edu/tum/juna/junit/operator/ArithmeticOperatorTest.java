package edu.tum.juna.junit.operator;

import static edu.tum.juna.Preconditions.checkArguments;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.operator.BinaryOperator;
import edu.tum.juna.operator.UnaryOperator;
import edu.tum.juna.operator.arithmetic.AddOperator;
import edu.tum.juna.operator.arithmetic.DivOperator;
import edu.tum.juna.operator.arithmetic.ModOperator;
import edu.tum.juna.operator.arithmetic.MulOperator;
import edu.tum.juna.operator.arithmetic.PowOperator;
import edu.tum.juna.operator.arithmetic.SubOperator;
import edu.tum.juna.operator.arithmetic.UnmOperator;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

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
	public void testApplyMeta() {
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

	private void testUnaryOperator(UnaryOperator op, double op1, double result) {
		assertEquals(result, op.apply(op1));
		assertEquals(result, op.apply(Double.toString(op1)));

		for (Object inOp : invalidObjects) {
			try {
				op.apply(inOp);
			} catch (LuaRuntimeException ex) {
				continue;
			}

			fail("Missing NoSuchMethodException exception");
		}
	}

	private void testBinaryOperator(BinaryOperator op, double op1, double op2, double result) {
		assertEquals(result, op.apply(op1, op2));
		assertEquals(result, op.apply(Double.toString(op1), op2));
		assertEquals(result, op.apply(op1, Double.toString(op2)));
		assertEquals(result, op.apply(Double.toString(op1), Double.toString(op2)));

		for (Object inOp1 : invalidObjects) {
			for (Object inOp2 : invalidObjects) {
				try {
					op.apply(inOp1, inOp2);
				} catch (LuaRuntimeException ex) {
					continue;
				}

				fail("Missing NoSuchMethodException exception");
			}
		}
	}

}
