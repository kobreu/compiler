package edu.tum.lua.junit;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
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
		assertEquals("Translating a String", "Hello World", (String) p.apply(l).get(0));
		
		l = new LinkedList<Object>();
		l.add(new Integer(5));
		assertEquals("Translating a Integer", "5", (String) p.apply(l).get(0));
		
		l = new LinkedList<Object>();
		l.add(new Double(2.5));
		assertEquals("Translating a Double", Double.toString(new Double(2.5)), (String) p.apply(l).get(0));
		
		l = new LinkedList<Object>();
		l.add(new Boolean(false));
		assertEquals("Translating a Boolean", Boolean.toString(new Boolean(false)), (String) p.apply(l).get(0));
		
		l = new LinkedList<Object>();
		l.add(null);
		assertEquals("Translating a nil", "nil", (String) p.apply(l).get(0));
		
		// TODO Check for Function, table --> How to implement the Lua-id? is this the Object-Id in Java
	}

}
