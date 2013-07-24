package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.Rawset;
import edu.tum.lua.types.LuaTable;

public class RawsetTest {

	@Test
	public void test() {
		Rawset r = new Rawset();

		try {
			r.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}

		try {
			r.apply("a");
			fail("accept other type than table as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}

		LuaTable table = new LuaTable();

		try {
			r.apply(table, null, 1.0);
			fail("accept nil as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept nil as second argument", true);
		}

		try {
			assertEquals(((LuaTable) r.apply(table, 1.0, "a").get(0)).get(1.0), "a");
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
