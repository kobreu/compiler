package edu.tum.juna.junit.stdlib.coroutine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.LocalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.parser.ParserUtil;

public class CoroutineTest {

	private LocalEnvironment environment;

	@Before
	public void setUp() {
		environment = new LocalEnvironment();
	}

	@Test
	public void test() throws Exception {

		setUp();

		Block blockfunction = ParserUtil.loadString("f = function() while true do x = x+1 coroutine.yield() end end");
		Block assign = ParserUtil.loadString("x=0");
		Block test = ParserUtil.loadString("c = coroutine.create(f) d=coroutine.resume(c)");

		LuaInterpreter.eval(assign, environment);
		assertEquals(0.0, environment.get("x"));

		LuaInterpreter.eval(blockfunction, environment);
		LuaInterpreter.eval(test, environment);
		assertEquals(1.0, environment.get("x"));

		Block test2 = ParserUtil.loadString("a = coroutine.status(c)");
		LuaInterpreter.eval(test2, environment);
		assertEquals("suspended", environment.get("a"));
		assertTrue((boolean) environment.get("d"));
	}

}
