package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.stdlib.math.Abs;
import edu.tum.lua.stdlib.math.Acos;
import edu.tum.lua.stdlib.math.Max;
import edu.tum.lua.stdlib.math.Min;
import edu.tum.lua.stdlib.math.Pow;
import edu.tum.lua.stdlib.math.Rad;
import edu.tum.lua.stdlib.math.Random;

public class MathTest {

	Abs abs;
	Acos acos;
	Max max;
	Min min;
	Pow pow;
	Rad rad;
	Random random;

	@Before
	public void setUp() throws Exception {

		abs = new Abs();
		acos = new Acos();

		max = new Max();
		min = new Min();
		pow = new Pow();
		rad = new Rad();
		random = new Random();
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

		// Max
		assertEquals(5.0, max.apply(3.0, 5.0, 4.0).get(0));

		// Min
		assertEquals(4.0, min.apply(10.0, 4.0, 5.0, 4.0).get(0));

		// Modf
		// TODO modf not implemented yet

		// Pow
		assertEquals(8.0, pow.apply(2.0, 3.0).get(0));
		assertEquals(-8.0, pow.apply(-2.0, 3.0).get(0));
		assertEquals(8.0, pow.apply(2.0, 3.0).get(0));
		assertEquals(0.125, pow.apply(2.0, -3.0).get(0));
		assertEquals(-0.125, pow.apply(-2.0, -3.0).get(0));

		// Rad
		assertEquals(Math.PI, rad.apply(180.0).get(0));
		assertEquals(Math.PI / 2, rad.apply(90.0).get(0));

		// Random
		assertEquals(1.0, random.apply(0.6).get(0));
		assertEquals(1.0, random.apply(1.0).get(0));
		assertEquals(1.0, random.apply(1.4).get(0));
		assertEquals(0.0, random.apply(0.0, 0.0).get(0));
		assertEquals(2.0, random.apply(2.0, 2.0).get(0));
		// TODO maybe some statistical tests?

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

		// Random
		try {
			random.apply(0.0);
			fail("don't accept empty interval");
		} catch (LuaBadArgumentException e) {
		}
		try {
			random.apply(2.0, 1.0);
			fail("don't accept empty interval");
		} catch (LuaBadArgumentException e) {
		}

		// TODO test some more, especially the functions that take more than one
		// argument
	}
}
