package edu.tum.lua.junit.stdlib.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.os.Rename;

public class RenameTest {

	@Test
	public void emptyArgumentTest() {
		Rename r = new Rename();
		try {
			r.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Rename r = new Rename();
		try {
			r.apply(false, "a");
			fail("accept other type than string as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than string as first argument", true);
		}
	}

	@Test
	public void secondArgumentTest() {
		Rename r = new Rename();
		try {
			r.apply("a");
			fail("accept only one argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept only one argument", true);
		}
		try {
			r.apply("a", false);
			fail("accept other type than string as second argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than string as second argument", true);
		}
	}

	@Test
	public void functionalTest() {
		Rename r = new Rename();
		List<Object> result = r.apply("a", "b");
		assertEquals(null, result.get(0));
		assertEquals("No such File or Directory", result.get(1));
	}

}
