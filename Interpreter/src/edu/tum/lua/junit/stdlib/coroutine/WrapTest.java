package edu.tum.lua.junit.stdlib.coroutine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.stdlib.coroutine.Wrap;
import edu.tum.lua.types.LuaFunction;

public class WrapTest {

	@Test
	public void emptyArgumentTest() {
		Wrap w = new Wrap();
		try {
			w.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Wrap w = new Wrap();
		try {
			w.apply("a");
			fail("accept other type than function as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than function as first argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Wrap w = new Wrap();
		LuaFunction f = (LuaFunction) w.apply((new ToString())).get(0);
		try {
			List<Object> l = f.apply("a");
			assertTrue((boolean) l.get(0));
			assertEquals("a", l.get(1));
		} catch (LuaRuntimeException e) {
			fail();
		}
	}
}
