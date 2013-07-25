package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.stdlib.math.Abs;
import edu.tum.lua.stdlib.math.Acos;

public class MathTest {

	Abs abs;
	Acos acos;

	@Before
	public void setUp() throws Exception {

		abs = new Abs();
		acos = new Acos();

	}

	@Test
	public void testMath() {

		// Abs
		assertEquals(5.0, abs.apply(5.0).get(0));
		assertEquals(5.0, abs.apply(-5.0).get(0));

		// Acos
		assertEquals(Math.PI, acos.apply(-1.0).get(0));
		assertEquals(Math.PI / 2, acos.apply(0.0).get(0));
		assertEquals(0.0, acos.apply(1.0).get(0));
		// TODO acos only defined on [-1,1]
		// assertEquals(NaN, acos.apply(-1.01));
		// assertEquals(Nan, acos.apply(1.01));

	}

	@Test
	public void testBadArguments() {

		// Abs
		try {
			// TODO test against all values except number
			abs.apply("string");
			fail("don't accept a string");
		} catch (LuaBadArgumentException e) {
		}

		// TODO test some more, especially the functions that take more than one
		// argument
	}
}
