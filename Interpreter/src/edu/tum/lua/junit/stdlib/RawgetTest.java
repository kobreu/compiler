package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.Rawget;
import edu.tum.lua.types.LuaTable;

public class RawgetTest {

	@Test
	public void test() {
		Rawget r = new Rawget();
		LinkedList<Object> l =new LinkedList<Object>();
		
		try{
			r.apply(l);
			fail("accept empty argument");
		}
		catch(LuaRuntimeException e){
			assertTrue("don't accept empty argument", true);
		}
		
		l.add("a");
		l.add(1);
		
		try{
			r.apply(l);
			fail("accept an other type than table as first argument");
		}
		catch(LuaRuntimeException e){
			assertTrue("don't accept empty argument",true);
		}
		
		l.removeAll(l);
		LuaTable table = new LuaTable();
		table.set(1.0, "a");
		table.set(2.0, "b");
		l.add(table);
		
		try{
			r.apply(l);
			fail("accept only one argument");
		}
		catch(LuaRuntimeException e){
			assertTrue("don't accept only one argument",true);
		}
		
		try{
			l.addLast(1.0);
			assertEquals((String) r.apply(l).get(0),"a");
			l.removeLast();
			l.addLast(3.0);
			assertEquals(r.apply(l).get(0),null);
			l = new LinkedList<Object>();
			l.add(new LuaTable());
			l.add("a");
			assertEquals(r.apply(l).get(0),null);
		}
		catch(LuaRuntimeException e){
			fail();
		}
	}
}
