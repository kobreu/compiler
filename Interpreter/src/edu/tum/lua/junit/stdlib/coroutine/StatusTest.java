package edu.tum.lua.junit.stdlib.coroutine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.stdlib.coroutine.Create;
import edu.tum.lua.stdlib.coroutine.Resume;
import edu.tum.lua.stdlib.coroutine.Status;

public class StatusTest {

	@Test
	public void emptyArgumentTest() {
		Status s = new Status();
		try {
			s.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Status s = new Status();
		try {
			s.apply("a");
			fail("accept other type than thread as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than thread as first argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Status s = new Status();
		Create c = new Create();
		Resume r = new Resume();
		Object thread = c.apply((new ToString())).get(0);
		try {
			assertEquals("suspended", s.apply(thread).get(0).toString());
			r.apply(thread, "a");
			assertEquals("dead", s.apply(thread).get(0).toString());
		} catch (LuaRuntimeException e) {
			fail();
		}
	}
}
