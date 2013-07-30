package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.stdlib.Error;

public class ErrorTest {

	@Test
	public void test() {
		Error e = new Error();
		try {
			e.apply(Collections.emptyList());
		} catch (LuaBadArgumentException b) {
			assertTrue("don't accept empty argument", true);
		} catch (LuaRuntimeException b) {
			fail("accept empty argument");
		}

		try {
			e.apply("error");
		} catch (LuaBadArgumentException b) {
			fail();
		} catch (LuaRuntimeException b) {
			assertEquals(b.getMessage(), "error");
		}
	}

}
