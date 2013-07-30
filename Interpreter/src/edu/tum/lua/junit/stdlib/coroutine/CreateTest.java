package edu.tum.lua.junit.stdlib.coroutine;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.coroutine.Create;

public class CreateTest {

	@Test
	public void emptyArgumentTest() {
		Create c = new Create();
		try {
			c.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Create c = new Create();
		try {
			c.apply("a");
			fail("accept other type than function as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than function as first argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Create c = new Create();
		try {
			assertTrue(c.apply(c).get(0) instanceof Thread);
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
