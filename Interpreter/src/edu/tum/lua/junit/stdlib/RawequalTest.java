package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.RawEqual;

public class RawequalTest {

	@Test
	public void test() {
		RawEqual r = new RawEqual();
		LinkedList<Object> l = new LinkedList<Object>();

		try {
			r.apply(l);
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}

		l.add("a");
		l.add("a");

		try {
			assertEquals("good behavior for two equals arguments", true, (boolean) r.apply(l).get(0));
		} catch (LuaRuntimeException e) {
			fail("don't accept only two arguments");
		}

		l.removeFirst();
		l.add("b");

		try {
			assertEquals("good behavior for two nonequals arguments", false, (boolean) r.apply(l).get(0));
		} catch (LuaRuntimeException e) {
			fail("don't accept only two arguments");
		}

	}

}
