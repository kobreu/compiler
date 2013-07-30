package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.Select;

public class SelectTest {

	@Test
	public void test() {
		Select s = new Select();
		LinkedList<Object> l1 = new LinkedList<Object>();
		double d1 = 1;
		l1.addFirst(d1);
		l1.addLast("a");
		l1.addLast("b");
		LinkedList<Object> l2 = new LinkedList<Object>();
		l2.addFirst("#");
		l2.addLast("a");
		l2.addLast("b");
		double d0 = 0;
		LinkedList<Object> l4 = new LinkedList<Object>();
		l4.add("#");
		
		try{
			assertEquals(s.apply(l1).get(0), s.apply(l2).get(0));
			assertEquals(s.apply(l4).get(0),d0);
		}
		catch (LuaRuntimeException e){
			fail();
		}
		
		LinkedList<Object> l3 = new LinkedList<Object>();
		
		l3.addFirst(d0);
		l3.addLast("a");
		LinkedList<Object> l6 = new LinkedList<Object>();
		l6.addFirst("ed");
		l6.addLast("a");
		
		LinkedList<LinkedList<Object>> errorObjects = new LinkedList<LinkedList<Object>>();
		errorObjects.add(l3);
		errorObjects.add(l6);
		
		for (LinkedList<Object> l : errorObjects){
			try{
				s.apply(l);
			}
			catch (LuaRuntimeException e){
				continue;
			}
			fail("Missing LuaRuntimeException");
		}

	}
}
