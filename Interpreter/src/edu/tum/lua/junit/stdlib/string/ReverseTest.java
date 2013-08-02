package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.lua.stdlib.string.Reverse;

public class ReverseTest {

	@Test
	public void test() {
		Reverse f = new Reverse();

		assertEquals("dlroW olleH", f.apply("Hello World").get(0));

		assertEquals("6.5", f.apply(new Double(5.6)).get(0));
		assertEquals("5", f.apply(new Double(5)).get(0));
	}
}
