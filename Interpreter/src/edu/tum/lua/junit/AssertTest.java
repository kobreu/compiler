package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.stdlib.Assert;
import edu.tum.lua.types.LuaTable;

public class AssertTest {

	// TODO write Unit tests
	// > next() >> exception

	Assert assertfunction;
	LuaTable testTable;

	@Before
	public void setUp() {
		assertfunction = new Assert();
	}

	@Test
	public void testApplyListOfObject() {
		assertEquals(null, assertfunction.apply(true));
		assertEquals(null, assertfunction.apply(true, "some message"));
	}

	@Test(expected = LuaRuntimeException.class)
	public void testApply() {
		assertfunction.apply(false);
	}
}
