package edu.tum.juna.junit.stdlib.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.stdlib.table.Insert;
import edu.tum.juna.types.LuaTable;

public class InsertTest {

	@Test
	public void emptyArgumentTest() {
		Insert i = new Insert();
		try {
			i.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Insert i = new Insert();
		try {
			i.apply("a");
			fail("accept other type than table as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than table as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Insert i = new Insert();
		try {
			i.apply(new LuaTable(), "a", "a");
			fail("accept other type than number as second argument when there are three arguments");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number as second argument when there are three arguments", true);
		}
	}

	@Test
	public void functionalTest() {
		Insert i = new Insert();
		LuaTable table = new LuaTable();
		try {
			i.apply(table, "a");
			assertEquals(table.get(1.0).toString(), "a");
			i.apply(table, 1.0, "b");
			assertEquals(table.get(1.0).toString(), "b");
			assertEquals(table.get(2.0).toString(), "a");
			i.apply(table, 4.0, "c");
			assertEquals(table.get(4.0).toString(), "c");
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
