package edu.tum.lua.junit.stdlib.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.table.Concat;
import edu.tum.lua.types.LuaTable;

public class ConcatTest {

	@Test
	public void emptyArgumentTest() {
		Concat c = new Concat();
		try {
			c.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Concat c = new Concat();
		try {
			c.apply(1.0);
			fail("accept other type than table as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than table as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Concat c = new Concat();
		LuaTable table = new LuaTable();
		try {
			c.apply(table, false);
			fail("accept other type than string or number as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than string or number as second argument", true);
		}
	}

	@Test
	public void thirdArgumentTest() {
		Concat c = new Concat();
		LuaTable table = new LuaTable();
		try {
			c.apply(table, " ", "a");
			fail("accept other type than number as third argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number as third argument", true);
		}
	}

	@Test
	public void fourthArgumentTest() {
		Concat c = new Concat();
		LuaTable table = new LuaTable();
		try {
			c.apply(table, " ", 1.0, "a");
			fail("accept other type than number as fourth argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number as fourth argument", true);
		}
	}

	@Test
	public void rightFunctionalTest() {
		Concat c = new Concat();
		LuaTable table = new LuaTable();
		table.set(1.0, "a");
		table.set(2.0, "b");
		table.set(3.0, "c");

		assertEquals(c.apply(table).get(0).toString(), "abc");
		assertEquals(c.apply(table, " ").get(0).toString(), "a b c");
		assertEquals(c.apply(table, " ", 2.0).get(0).toString(), "b c");
		assertEquals(c.apply(table, " ", 2.0, 2.0).get(0).toString(), "b");
	}

	@Test
	public void errorFunctionalTest() {
		Concat c = new Concat();
		LuaTable table = new LuaTable();
		table.set(1.0, "a");
		table.set(3.0, "c");
		try {
			c.apply(table, " ", 1.0, 3.0);
			fail();
		} catch (LuaRuntimeException e) {
		}
		try {
			c.apply(table, " ", 0.0);
			fail();
		} catch (LuaRuntimeException e) {
		}
	}
}
