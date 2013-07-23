package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.ast.FunctionNode;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaTable;

public class LuaTableTest {

	LuaTable table;

	@Before
	public void setUp() {
		table = new LuaTable();
	}

	@Test
	public void testGet() {
		LuaTable table = new LuaTable();
		LuaTable emptyTable = new LuaTable();

		// Boolean
		table.set(true, "value1");
		table.set(false, "value2");
		assertEquals("value1", table.get(true));
		assertEquals("value2", table.get(false));
		assertEquals(null, emptyTable.get(true));
		assertEquals(null, emptyTable.get(false));

		// Number
		table.set(1.2, "value3");
		assertEquals("value3", table.get(1.2));
		assertEquals(null, emptyTable.get(1.2));

		// String
		table.set("string", "value4");
		assertEquals("value4", table.get("string"));
		assertEquals(null, emptyTable.get("string"));

		// Table
		LuaTable tmpTable = new LuaTable();
		table.set(tmpTable, "value6");
		assertEquals("value6", table.get(tmpTable));
		assertEquals(null, emptyTable.get(tmpTable));

		// Function
		LuaFunction tmpFunction = new LuaFunctionInterpreted(null, new FunctionNode());
		table.set(tmpFunction, "value5");
		assertEquals("value5", table.get(tmpFunction));
		assertEquals(null, emptyTable.get(tmpFunction));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNil() {
		table.get(null);
	}

	@Test
	public void testSetGetBoolean() {
		table.set("a", true);
		assertTrue(table.getBoolean("a"));
	}

	@Test
	public void testSetGetLuaFunction() {
		LuaFunction f = new LuaFunctionInterpreted(null, new FunctionNode());
		table.set("a", f);
		assertEquals(f, table.getLuaFunction("a"));
	}

	@Test
	public void testSetGetLuaTable() {
		LuaTable t = new LuaTable();
		table.set("a", t);
		assertEquals(t, table.getLuaTable("a"));
	}

	@Test
	public void testSetGetNumber() {
		table.set("a", 1.0);
		assertTrue(1.0 == table.getNumber("a"));
	}

	@Test
	public void testSetGetString() {
		table.set("a", "b");
		assertEquals("b", table.getString("a"));
	}

	@Test
	public void testSetIndex() {
		LuaTable t1 = new LuaTable();
		LuaTable t2 = new LuaTable();
		t1.setIndex(t2);

		t1.set("t1", 1.0);
		t2.set("t2", 2.0);

		assertEquals(1.0, t1.get("t1"));
		assertEquals(2.0, t1.get("t2"));

		fail("Forward Method search - Not yet implemented");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNil() {
		table.set(null, "a");
	}

	@Test
	public void testUnset() {
		table.set("a", "a");
		assertTrue(null != table.get("a"));
		table.unset("a");
		assertTrue(null == table.get("a"));
	}

}
