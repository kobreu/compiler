package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaNotSupportedException;
import edu.tum.lua.stdlib.Getfenv;

public class GetfenvTest {

	Getfenv getfenv;

	@Before
	public void setup() {
		GlobalEnvironment global = new GlobalEnvironment();
		getfenv = (Getfenv) global.getLuaFunction("getfenv");
	}

	@Test
	public void emptyArgumentTest() {
		assertTrue(getfenv.apply(Collections.emptyList()).get(0) instanceof GlobalEnvironment);
	}

	@Test(expected = LuaNotSupportedException.class)
	public void numberInputTest() {
		getfenv.apply(1.0);
	}

	@Test(expected = LuaBadArgumentException.class)
	public void wrongInputTest() {
		getfenv.apply("a");
	}

	@Test
	public void functionnalTest() {
		assertTrue(getfenv.apply(getfenv).get(0) instanceof GlobalEnvironment);
	}
}
