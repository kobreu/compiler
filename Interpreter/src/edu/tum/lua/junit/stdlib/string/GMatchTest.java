package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import edu.tum.lua.stdlib.string.GMatch;
import edu.tum.lua.types.LuaFunction;

public class GMatchTest {

	@Test
	public void test() {

		// Will work if Pairs and IPairs work

		GMatch f = new GMatch();

		String s = "Hello out there";
		String p = "%a+";

		List<Object> list = f.apply(s, p);
		LuaFunction g = ((LuaFunction) list.get(0));

		assertEquals(p, list.get(1));
		assertEquals(s, list.get(2));

		list = g.apply(list.get(1), list.get(2));

		assertEquals("Hello", list.get(0));
		assertEquals(" out there", list.get(1));

	}
}
