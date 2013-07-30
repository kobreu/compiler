package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.Getfenv;

public class GetfenvTest {

	@Test
	public void emptyArgumentTest() {
		Getfenv g = new Getfenv();
		try {
			assertTrue("accept empty argument", g.apply(Collections.emptyList()).get(0) instanceof GlobalEnvironment);
		} catch (LuaRuntimeException e) {
			fail("don't accept empty argument");
		}
	}

	@Test
	public void numberInputTest() {
		Getfenv g = new Getfenv();
		try {
			g.apply(1.0);
			fail();
		} catch (LuaRuntimeException e) {
			assertEquals("not supported in this version", e.getMessage());
		}
	}

	@Test
	public void wrongInputTest() {
		Getfenv g = new Getfenv();
		try {
			g.apply("a");
			fail();
		} catch (LuaBadArgumentException e) {
			assertTrue("don't accept other type than function or number as input", true);
		}
	}

	@Test
	public void functionnalTest() {
		Getfenv g = new Getfenv();
		try {
			assertTrue(g.apply(g).get(0) instanceof GlobalEnvironment);
		} catch (LuaRuntimeException e) {
			fail();
		}
	}

}
