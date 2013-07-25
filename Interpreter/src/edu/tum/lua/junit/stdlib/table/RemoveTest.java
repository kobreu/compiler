package edu.tum.lua.junit.stdlib.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.table.MaxN;
import edu.tum.lua.stdlib.table.Remove;
import edu.tum.lua.types.LuaTable;

public class RemoveTest {

	@Test
	public void emptyArgumentTest() {
		Remove r = new Remove();
		try {
			r.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Remove r = new Remove();
		try {
			r.apply("a");
			fail("accept other type than table as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than table as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Remove r = new Remove();
		try {
			r.apply(new LuaTable(), "a");
			fail("accept other type than number as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number as second argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Remove r = new Remove();
		MaxN op = new MaxN();
		LuaTable table = new LuaTable();
		table.set(1.0, "a");
		table.set(2.0, "b");
		table.set(4.0, "c");
		table.set(5.0, "e");
		try {
			assertEquals("e", r.apply(table).get(0));
			assertEquals(4.0, (double) op.apply(table).get(0), 0.0);
			assertEquals(null, r.apply(table, 3.0).get(0));
			assertEquals("c", table.get(3.0));
			assertEquals(0, r.apply(table, 4.0).size());
			assertEquals(0, r.apply(table, 0.0).size());

		} catch (LuaRuntimeException e) {
			fail();
		}
	}
}
