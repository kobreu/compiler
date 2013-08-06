package edu.tum.juna.junit.stdlib.coroutine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.stdlib.coroutine.Create;
import edu.tum.juna.stdlib.coroutine.Resume;

public class ResumeTest {

	@Test
	public void emptyArgumentTest() {
		Resume r = new Resume();
		try {
			r.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Resume r = new Resume();
		try {
			r.apply("a");
			fail("accept other type than thread as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than thread as first argument", true);
		}
	}

	@Test
	public void functionnalTest() {
		Create c = new Create();
		Resume r = new Resume();
		Object thread = c.apply((new ToString())).get(0);
		try {
			List<Object> l = r.apply(thread, "a");
			assertTrue((boolean) l.get(0));
			assertEquals("a", l.get(1));
		} catch (LuaRuntimeException e) {
			fail();
		}
	}
}
