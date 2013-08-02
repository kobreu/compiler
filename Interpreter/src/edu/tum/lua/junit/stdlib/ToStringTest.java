package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.ToString;

public class ToStringTest {

	@Test
	public void test() {
		ToString p = new ToString();
		LinkedList<Object> l;

		l = new LinkedList<Object>();
		try {
			p.apply(l);
			fail("Accept empty input");
		} catch (LuaRuntimeException e) {
			assertTrue("Don't accept empty input", true);
		} catch (Exception e) {
			fail("Unknow exception");
		}

		l.add("Hello World");
		assertEquals("Translating a String", "Hello World", p.apply(l).get(0));

		l = new LinkedList<Object>();
		l.add(new Double(2.5));
		assertEquals("Translating a Double", Double.toString(new Double(2.5)), p.apply(l).get(0));

		l = new LinkedList<Object>();
		l.add(new Boolean(false));
		assertEquals("Translating a Boolean", Boolean.toString(new Boolean(false)), p.apply(l).get(0));

		l = new LinkedList<Object>();
		l.add(null);
		assertEquals("Translating a nil", "nil", p.apply(l).get(0));

		l = new LinkedList<Object>();
		l.add("Hello World");
		l.add("fail");
		assertEquals("Translating a String", "Hello World", p.apply(l).get(0));

		assertEquals("Translating 5.0 as 5", "5", p.apply(new Double(5)).get(0));
	}

}
