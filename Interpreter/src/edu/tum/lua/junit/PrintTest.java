package edu.tum.lua.junit;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import edu.tum.lua.Environment;
import edu.tum.lua.ast.FunctionNode;
import edu.tum.lua.stdlib.Print;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaTable;

public class PrintTest {

	
	@Test
	public void test() {
		Print p = new Print();
		LinkedList<Object> l = new LinkedList<Object>();
		double d = 1;
		l.add(d);
		l.add("abc");
		l.add(null);
		l.add(true);
		l.add(new LuaTable());
		l.add(new LuaFunctionInterpreted(new Environment(null), new FunctionNode()));
		LinkedList<Object> l1 = new LinkedList<Object>();
		
		assertNull(p.apply(l));
		assertNull(p.apply(l1));
	}

}
