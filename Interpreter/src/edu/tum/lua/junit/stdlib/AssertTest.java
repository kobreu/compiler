package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.Assert;
import edu.tum.lua.types.LuaTable;

public class AssertTest {

	@Test
	public void emptyArgumentTest() {
		Assert a = new Assert();
		try {
			a.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Assert a = new Assert();
		try {
			a.apply(false, new LuaTable());
			fail("accept other type than string or number as second argument");
		} catch (LuaBadArgumentException e) {
			assertTrue("don't other type than string or number as second argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Assert a = new Assert();
		try {
			a.apply(false, "a");
			fail();
		} catch (LuaRuntimeException e) {
		}
		try {
			List<Object> l = a.apply("a", "b", "c");
			assertEquals(3, l.size());
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
