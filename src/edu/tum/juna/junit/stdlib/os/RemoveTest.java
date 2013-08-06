package edu.tum.juna.junit.stdlib.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.stdlib.os.Remove;

public class RemoveTest {

	@Test
	public void emptyArgumentTest() {
		Remove r = new Remove();
		try {
			r.apply(Collections.emptyList());
			fail("accept empty argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept empty argument", true);
		}
	}

	@Test
	public void firstArgumentTest() {
		Remove r = new Remove();
		try {
			r.apply(true);
			fail("accept other type than string as first argument");
		} catch (LuaRuntimeException e) {
			assertTrue("don't accept other type than string as first argument", true);
		}
	}

	@Test
	public void functionalTest() {
		Remove r = new Remove();
		List<Object> result = r.apply("a");
		assertEquals(null, result.get(0));
		assertEquals("No such File or Directory", result.get(1));
	}

}
