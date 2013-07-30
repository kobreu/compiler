package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import edu.tum.lua.stdlib.ExampleStdlibFunction;
=======
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.VoidFunction;
>>>>>>> Parser
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionNative;
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

<<<<<<< HEAD
=======
		// Nil
		assertEquals(null, table.get(null));

>>>>>>> Parser
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
<<<<<<< HEAD
		LuaFunction tmpFunction = new ExampleStdlibFunction();
		table.set(tmpFunction, "value5");
		assertEquals("value5", table.get(tmpFunction));
		assertEquals(null, emptyTable.get(tmpFunction));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNil() {
		table.get(null);
=======
		LuaFunction tmpFunction = new VoidFunction();
		table.set(tmpFunction, "value5");
		assertEquals("value5", table.get(tmpFunction));
		assertEquals(null, emptyTable.get(tmpFunction));
>>>>>>> Parser
	}

	@Test
	public void testSetGetBoolean() {
		table.set("a", true);
		assertTrue(table.getBoolean("a"));
	}

	@Test
	public void testSetGetLuaFunction() {
<<<<<<< HEAD
		LuaFunction f = new ExampleStdlibFunction();
=======
		LuaFunction f = new VoidFunction();
>>>>>>> Parser
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

	protected static class TestFunction extends LuaFunctionNative {
		@Override
		public List<Object> apply(List<Object> arguments) {
<<<<<<< HEAD
			assertEquals(1, arguments.size());
			String key = (String) arguments.get(0);
=======
			assertEquals(2, arguments.size());
			String key = (String) arguments.get(1);
>>>>>>> Parser
			return Collections.singletonList((Object) (key + "value"));
		}
	}

	@Test
	public void testSetIndex() {
		LuaTable t1 = new LuaTable();
		LuaTable t2 = new LuaTable();
<<<<<<< HEAD
		t1.setIndex(t2);

		t1.set("t1", 1.0);
		t2.set("t2", 2.0);

		assertEquals(1.0, t1.get("t1"));
		assertEquals(2.0, t1.get("t2"));

		t1.setIndex((LuaTable) null);
		assertEquals(null, t1.get("t2"));

		t1.setIndex(new TestFunction());
=======

		t1.setMetatable(new LuaTable());
		t1.setMetaIndex(t2);

		t1.set("t1", 1.0);
		t2.set("t2", 2.0);

		assertEquals(1.0, t1.get("t1"));
		assertEquals(2.0, t1.get("t2"));

		t1.setMetaIndex((LuaTable) null);
		assertEquals(null, t1.get("t2"));

		t1.setMetaIndex(new TestFunction());
>>>>>>> Parser
		assertEquals(1.0, t1.get("t1"));
		assertEquals("t2value", t1.get("t2"));
	}

<<<<<<< HEAD
	@Test(expected = IllegalArgumentException.class)
=======
	@Test(expected = LuaRuntimeException.class)
>>>>>>> Parser
	public void testSetNil() {
		table.set(null, "a");
	}

	@Test
	public void testUnset() {
		table.set("a", "a");
		assertTrue(null != table.get("a"));
<<<<<<< HEAD
		table.unset("a");
=======
		table.set("a", null);
>>>>>>> Parser
		assertTrue(null == table.get("a"));
	}

}
