package edu.tum.juna.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.stdlib.Unpack;
import edu.tum.juna.types.LuaTable;

public class UnpackTest {

	@Test
	public void emptyArgumentTest() {
		Unpack u = new Unpack();
		try {
			u.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void FirstArgumentTest() {
		Unpack u = new Unpack();
		try {
			u.apply("a");
			fail("accept other type than table for first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than table for first argument", true);
		}
	}

	@Test
	public void SecondArgumentTest() {
		Unpack u = new Unpack();
		LuaTable table = new LuaTable();
		try {
			u.apply(table, "a");
			fail("accept other type than number for second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number for second argument", true);
		}
	}

	@Test
	public void ThirdArgumentTest() {
		Unpack u = new Unpack();
		LuaTable table = new LuaTable();
		try {
			u.apply(table, 1.0, "a");
			fail("accept other type than number for third argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number for thirs argument", true);
		}
	}

	@Test
	public void functionalTest() {
		Unpack u = new Unpack();
		LuaTable table1 = new LuaTable();
		LuaTable table2 = new LuaTable();
		table1.set(1.0, "a");
		table1.set(2.0, "b");
		table1.set("c", "c");
		table2.set("a", "a");
		table2.set(2.0, "b");
		try {
			List<Object> l = u.apply(table1);
			assertEquals(l.get(0).toString(), "a");
			assertEquals(l.get(1).toString(), "b");
			assertEquals(l.size(), 2);
			l = u.apply(table1, 0.0);
			assertEquals(l.get(0), null);
			assertEquals(l.get(1).toString(), "a");
			assertEquals(l.get(2).toString(), "b");
			assertEquals(l.size(), 3);
			l = u.apply(table1, 1.0, 3.0);
			assertEquals(l.get(0).toString(), "a");
			assertEquals(l.get(1).toString(), "b");
			assertEquals(l.get(2), null);
			assertEquals(l.size(), 3);
			l = u.apply(table2);
			assertEquals(l.size(), 0);
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
