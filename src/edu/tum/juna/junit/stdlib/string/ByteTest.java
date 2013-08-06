package edu.tum.juna.junit.stdlib.string;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ByteTest {

	@Test
	public void test() {
		edu.tum.juna.stdlib.string.Byte f = new edu.tum.juna.stdlib.string.Byte();

		assertTrue(checkFunction(new int[] { 72 }, f.apply("Hello")));
		assertTrue(checkFunction(new int[] { 53 }, f.apply(new Double(5.5))));
		assertTrue(checkFunction(new int[] { 101 }, f.apply("Hello", new Double(2))));
		assertTrue(checkFunction(new int[] { 108 }, f.apply("Hello", new Double(3), new Double(3))));

		assertTrue(checkFunction(new int[] { 53, 46 }, f.apply("5.5", new Double(1), new Double(2))));
		assertTrue(checkFunction(new int[] { 53, 46 }, f.apply(new Double(5.5), new Double(1), new Double(2))));

		assertTrue(checkFunction(new int[] { 72, 101 }, f.apply("Hello", new Double(1.1), new Double(2))));
		assertTrue(checkFunction(new int[] { 101 }, f.apply("Hello", new Double(1.5), new Double(2))));
		assertTrue(checkFunction(new int[] { 101 }, f.apply("Hello", new Double(1.5), new Double(2.1))));
		assertTrue(checkFunction(new int[] { 101, 108 }, f.apply("Hello", new Double(1.5), new Double(2.6))));

		assertTrue(checkFunction(new int[] { 72 }, f.apply("H", new Double(1), new Double(2))));
		assertTrue(checkFunction(new int[] {}, f.apply("H", new Double(2))));
		assertTrue(checkFunction(new int[] {}, f.apply("H", new Double(3), new Double(2))));

		assertTrue(checkFunction(new int[] { 111 }, f.apply("Hello", new Double(-1))));
		assertTrue(checkFunction(new int[] { 108, 111 }, f.apply("Hello", new Double(-2), new Double(-1))));
		assertTrue(checkFunction(new int[] {}, f.apply("Hello", new Double(-2), new Double(1))));
	}

	private boolean checkFunction(int[] expectedValues, List<Object> returnedValues) {
		try {
			for (int i : expectedValues) {
				if (!returnedValues.contains(new Double(i))) {
					return false;
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		} catch (AssertionError e) {
			return true;
		}
	}

}
