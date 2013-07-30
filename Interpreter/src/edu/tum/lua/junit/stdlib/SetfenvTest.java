package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.Setfenv;
import edu.tum.lua.types.LuaTable;

public class SetfenvTest {

	@Test
	public void emptyArgumentTest() {
		Setfenv s = new Setfenv();
		try {
			s.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Setfenv s = new Setfenv();
		try {
			s.apply("a", new LuaTable());
			fail("accept other type than function or number as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than function or number as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Setfenv s = new Setfenv();
		try {
			s.apply(1.0, "a");
			fail("accept other type than table as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than table as second argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Setfenv s = new Setfenv();
		GlobalEnvironment env = new GlobalEnvironment();
		try {
			s.apply(1.0, env);
			fail();
		} catch (LuaRuntimeException e) {
			assertEquals("not supported in this version", e.getMessage());
		}
		try {
			s.apply(s, env);
			fail();
		} catch (LuaRuntimeException e) {
			assertEquals("not supported in this version", e.getMessage());
		}
	}
}
