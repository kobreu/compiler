package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LocalEnvironment;

public class LocalEnvironmentTest {

	@Test
	public void test() {
		GlobalEnvironment global = new GlobalEnvironment();
		LocalEnvironment local1 = new LocalEnvironment(global);
		LocalEnvironment local2 = new LocalEnvironment(local1);

		global.set("a", "a");
		local1.setLocal("b", "b");
		local2.setLocal("a", "x");
		local2.set("b", "x");
		local2.set("c", "c");

		assertEquals("a", global.get("a"));
		assertEquals(null, global.get("b"));
		assertEquals("c", global.get("c"));

		assertEquals("a", local1.get("a"));
		assertEquals("x", local1.get("b"));
		assertEquals("c", local1.get("c"));

		assertEquals("x", local2.get("a"));
		assertEquals("x", local2.get("b"));
		assertEquals("c", local2.get("c"));
	}

}
