package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.stdlib.ToNumber;

public class ToNumberTest {

	@Test
	public void test() {
		ToNumber p = new ToNumber();
		LinkedList<Object> l;

		l = new LinkedList<Object>();

		try {
			p.apply(l);
			fail("Accept empty input");
		} catch (Exception e) {
			assertTrue("Don't accept empty input", true);
		}

		l.add("1234");
		assertEquals("Translating a true int String", 1234, ((Double) p.apply(l).get(0)).intValue());

		l = new LinkedList<Object>();
		l.add("42.21");
		assertEquals("Translating a true double String", new Double(42.21), p.apply(l).get(0));

		l = new LinkedList<Object>();
		l.add("Hello123");
		assertEquals("Translating a false String", null, p.apply(l).get(0));

		l = new LinkedList<Object>();
		l.add("FF");
		l.add(new Double(16));
		assertEquals("Translating a String, Base", 255, ((Double) p.apply(l).get(0)).intValue());

		l = new LinkedList<Object>();
		l.add("10");
		l.add(new Double(16));
		assertEquals("Translating a String, Base", 16, ((Double) p.apply(l).get(0)).intValue());

		l = new LinkedList<Object>();
		l.add("Z");
		l.add(new Double(36));
		assertEquals("Translating a String, Base", 35, ((Double) p.apply(l).get(0)).intValue());

		l = new LinkedList<Object>();
		l.add("z");
		l.add(new Double(36));
		assertEquals("Translating a String, Base", 35, ((Double) p.apply(l).get(0)).intValue());

		l = new LinkedList<Object>();
		l.add(new Double(10));
		l.add(new Double(8));
		assertEquals("Translating a Number, Base", 8, ((Double) p.apply(l).get(0)).intValue());

		l = new LinkedList<Object>();
		l.add("F");
		l.add(new Double(5));
		assertEquals("Translating a Number, Base < Number", null, p.apply(l).get(0));
	}

}
