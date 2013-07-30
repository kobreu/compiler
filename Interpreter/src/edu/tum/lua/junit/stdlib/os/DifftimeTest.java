package edu.tum.lua.junit.stdlib.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.os.Difftime;

public class DifftimeTest {

	@Test
	public void emptyArgumentTest() {
		Difftime d = new Difftime();
		try {
			d.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Difftime d = new Difftime();
		try {
			d.apply("a", 1.0);
			fail("accept other type than number as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Difftime d = new Difftime();
		try {
			d.apply(1.0, "a");
			fail("accept other type than number as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than number as second argument", true);
		}
	}

	@Test
	public void functionalTest() throws Exception {
		Difftime d = new Difftime();
		assertEquals(1.0, d.apply(3.0, 2.0).get(0));
	}
}
