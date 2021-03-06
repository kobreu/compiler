package edu.tum.juna.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.stdlib.VoidFunction;
import edu.tum.juna.types.LuaFunction;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;

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

		// Nil
		assertEquals(null, table.get(null));

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
		LuaFunction tmpFunction = new VoidFunction();
		table.set(tmpFunction, "value5");
		assertEquals("value5", table.get(tmpFunction));
		assertEquals(null, emptyTable.get(tmpFunction));
	}

	@Test
	public void testSetGetBoolean() {
		table.set("a", true);
		assertTrue(table.getBoolean("a"));
	}

	@Test
	public void testSetGetLuaFunction() {
		LuaFunction f = new VoidFunction();
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
			assertEquals(2, arguments.size());
			String key = (String) arguments.get(1);
			return Collections.singletonList((Object) (key + "value"));
		}
	}

	@Test
	public void testSetIndex() {
		LuaTable t1 = new LuaTable();
		LuaTable t2 = new LuaTable();

		t1.setMetatable(new LuaTable());
		t1.setMetaIndex(t2);

		t1.set("t1", 1.0);
		t2.set("t2", 2.0);

		assertEquals(1.0, t1.get("t1"));
		assertEquals(2.0, t1.get("t2"));

		t1.setMetaIndex((LuaTable) null);
		assertEquals(null, t1.get("t2"));

		t1.setMetaIndex(new TestFunction());
		assertEquals(1.0, t1.get("t1"));
		assertEquals("t2value", t1.get("t2"));
	}

	@Test(expected = LuaRuntimeException.class)
	public void testSetNil() {
		table.set(null, "a");
	}

	@Test
	public void testUnset() {
		table.set("a", "a");
		assertTrue(null != table.get("a"));
		table.set("a", null);
		assertTrue(null == table.get("a"));
	}

}
