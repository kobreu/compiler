package edu.tum.lua.junit.stdlib.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.table.Sort;
import edu.tum.lua.types.LuaTable;

public class SortTest {

	@Test
	public void emptyArgumentTest() {
		Sort s = new Sort();
		try {
			s.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Sort s = new Sort();
		try {
			s.apply("a");
			fail("accept other type than table as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than table as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Sort s = new Sort();
		try {
			s.apply(new LuaTable(), "a");
			fail("accept other type than function as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than function as second argument", true);
		}
	}

	@Test
	public void functionnalNumberTest() {
		Sort s = new Sort();
		LuaTable table = new LuaTable();
		table.set(1.0, 2.0);
		table.set(2.0, 1.0);
		table.set(4.0, 0.0);
		try {
			s.apply(table);
			assertEquals(1.0, (double) table.get(1.0), 0.0);
			assertEquals(0.0, table.getNumber(4.0), 0.0);
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

	@Test
	public void functionalStringTest() {
		Sort s = new Sort();
		LuaTable table = new LuaTable();
		table.set(1.0, "ab");
		table.set(2.0, "a");
		table.set("a", "a");
		try {
			s.apply(table);
			assertEquals("a", table.get(1.0).toString());
			assertEquals("a", table.get("a").toString());
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

	@Test
	public void functionalErrorTest() {
		Sort s = new Sort();
		LuaTable table = new LuaTable();
		table.set(1.0, 1.0);
		table.set(2.0, "a");
		try {
			s.apply(table);
			fail("compare uncomparable object");
		} catch (LuaRuntimeException e) {
			assertTrue("don't compare uncomparable object", true);
		}
		table.set(1.0, new LuaTable());
		try {
			s.apply(table);
			fail("compare uncomparable object");
		} catch (LuaRuntimeException e) {
			assertTrue("don't compare uncomparable object", true);
		}
	}

}
