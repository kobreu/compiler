package edu.tum.juna.junit.stdlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.stdlib.math.Abs;
import edu.tum.juna.stdlib.math.Acos;
import edu.tum.juna.stdlib.math.Max;
import edu.tum.juna.stdlib.math.Min;
import edu.tum.juna.stdlib.math.Modf;
import edu.tum.juna.stdlib.math.Pow;
import edu.tum.juna.stdlib.math.Rad;
import edu.tum.juna.stdlib.math.Random;

public class MathTest {

	Abs abs;
	Acos acos;
	Max max;
	Min min;
	Modf modf;
	Pow pow;
	Rad rad;
	Random random;

	@Before
	public void setUp() throws Exception {

		abs = new Abs();
		acos = new Acos();

		max = new Max();
		min = new Min();
		modf = new Modf();
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
		// acos only defined on [-1,1]
		assertEquals(Double.NaN, acos.apply(-1.01).get(0));
		assertEquals(Double.NaN, acos.apply(1.01).get(0));

		// Max
		assertEquals(5.0, max.apply(3.0, 5.0, 4.0).get(0));

		// Min
		assertEquals(4.0, min.apply(10.0, 4.0, 5.0, 4.0).get(0));

		// Modf
		assertEquals(10.0, modf.apply(10.7).get(0));
		assertEquals(0.7, modf.apply(10.7).get(1));
		assertEquals(20.0, modf.apply(20.3).get(0));
		assertEquals(0.3, modf.apply(20.3).get(1));

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
