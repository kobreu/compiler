package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.operator.list.LengthOperator;
import edu.tum.lua.types.LuaTable;

public class LengthOperatorTest {

	LengthOperator op;

	@Before
	public void setUp() throws Exception {
		op = new LengthOperator();
	}

	@Test
	public void test() {
		LengthOperator op = new LengthOperator();
		String op1 = "abc";
		LuaTable op2 = new LuaTable();
		double d1 = 1;
		double d3 = 3;
		op2.set(d1, d3);
		op2.set(d3, "leng");
		LuaTable op3 = new LuaTable();
		op3.set(d1, d1);
		double d2 = 2;
		op3.set(d2, d2);
		op3.set("key", d3);
		try {
			assertEquals(op1.length(), op.apply(op1));
			assertEquals(d1, op.apply(op2));
			assertEquals(d2, op.apply(op3));
		} catch (NoSuchMethodException e) {
			fail();
		}

		Object[] invalidObjects = { null, false, true, 2.0 };

		for (Object inOp1 : invalidObjects) {
			try {
				op.apply(inOp1);
			} catch (NoSuchMethodException ex) {
				continue;
			}

			fail("Missing NoSuchMethodException exception");
		}
	}

}
