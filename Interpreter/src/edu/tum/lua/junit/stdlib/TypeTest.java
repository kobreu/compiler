package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.Select;
import edu.tum.lua.stdlib.Type;
import edu.tum.lua.types.LuaTable;

public class TypeTest {

	@Test
	public void test() {
		Type t = new Type();

		try {
			t.apply(new LinkedList<Object>());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}

		try {
			assertEquals(t.apply(1.0).get(0), "number");
		} catch (LuaRuntimeException e) {
			fail("uncorrect behavior for number");
		}

		try {
			assertEquals(t.apply("abc").get(0), "string");
		} catch (LuaRuntimeException e) {
			fail("uncorrect behavior for string");
		}

		try {
			assertEquals(t.apply(true).get(0), "boolean");
		} catch (LuaRuntimeException e) {
			fail("uncorrect behavior for boolean");
		}

		try {
			assertEquals(t.apply(new Select()).get(0), "function");
		} catch (LuaRuntimeException e) {
			fail("uncorrect behavior for function");
		}

		try {
			assertEquals(t.apply(new LuaTable()).get(0), "table");
		} catch (LuaRuntimeException e) {
			fail("uncorrect behavior for table");
		}

		try {
			assertEquals(t.apply(Collections.singletonList(null)).get(0), "nil");
		} catch (LuaRuntimeException e) {
			fail("uncorrect behavior for nil");
		}
	}

}
