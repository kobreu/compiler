package edu.tum.lua.junit.operator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.operator.relational.EqOperator;
import edu.tum.lua.operator.relational.LEOperator;
import edu.tum.lua.operator.relational.LTOperator;
import edu.tum.lua.stdlib.VoidFunction;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;

public class RelationalOperatorTest {

	EqOperator eq;
	LEOperator le;
	LTOperator lt;

	private final Object[] luaObjects = { null, false, "a", 1.0, new LuaTable(), new VoidFunction() };

	@Before
	public void setUp() throws Exception {
		eq = new EqOperator();
		le = new LEOperator();
		lt = new LTOperator();
	}

	@Test
	public void testApply() throws NoSuchMethodException {

		/*
		 * Equal ==
		 */

		// Two Numbers
		assertEquals(true, eq.apply(1.0, 1.0));
		assertEquals(false, eq.apply(1.0, 2.0));

		// Two Strings
		assertEquals(true, eq.apply("string", "string"));
		assertEquals(false, eq.apply("string1", "string2"));

		// Two tables
		LuaTable t1 = new LuaTable();
		LuaTable t2 = new LuaTable();
		assertEquals(true, eq.apply(t1, t1));
		assertEquals(false, eq.apply(t1, t2));

		// Two functions
		LuaFunction f1 = new VoidFunction();
		LuaFunction f2 = new VoidFunction();
		assertEquals(true, eq.apply(f1, f1));
		assertEquals(false, eq.apply(f1, f2));

		// All different LuaTypes against each other
		for (Object luaObject1 : luaObjects) {
			for (Object luaObject2 : luaObjects) {
				assertEquals(luaObject1 == luaObject2, eq.apply(luaObject1, luaObject2));
			}
		}

		/*
		 * Less or Equal <=
		 */
		assertEquals(true, le.apply(4.0, 5.0));
		assertEquals(true, le.apply(5.0, 5.0));
		assertEquals(false, le.apply(6.0, 5.0));

		/*
		 * Less than <
		 */
		assertEquals(true, lt.apply(4.0, 5.0));
		assertEquals(false, lt.apply(5.0, 5.0));
		assertEquals(false, lt.apply(6.0, 5.0));

	}

}
