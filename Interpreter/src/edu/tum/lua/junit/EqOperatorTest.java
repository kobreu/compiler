package edu.tum.lua.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.ast.FunctionNode;
import edu.tum.lua.operator.relational.EqOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;

public class EqOperatorTest {

	EqOperator eq;

	private final Object[] luaObjects = { null, false, "a", 1.0,
			new LuaTable(), new LuaFunction(new LuaTable(), new FunctionNode()) };

	@Before
	public void setUp() throws Exception {
		eq = new EqOperator();
	}

	@Test
	public void testApply() {

		try {

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
			LuaFunction f1 = new LuaFunction(new LuaTable(), new FunctionNode());
			LuaFunction f2 = new LuaFunction(new LuaTable(), new FunctionNode());
			assertEquals(true, eq.apply(f1, f1));
			assertEquals(false, eq.apply(f1, f2));

			// All different LuaTypes against each other
			for (Object luaObject1 : luaObjects) {
				for (Object luaObject2 : luaObjects) {

					if (luaObject1.getClass() != luaObject2.getClass()) {
						assertEquals(false, eq.apply(luaObject1, luaObject2));
					}

				}
			}

		} catch (NoSuchMethodException e) {
			fail();
		}

	}

}
