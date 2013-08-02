package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.lua.stdlib.string.Upper;

public class UpperTest {

	@Test
	public void test() {
		Upper f = new Upper();

		assertEquals("Translating to upper", "HELLO WORLD", f.apply("Hello World").get(0));
		assertEquals("Translating to upper", "HELLO WORLD", f.apply("hello world").get(0));
		assertEquals("Translating to upper", "HELLO WORLD", f.apply("HELLO WORLD").get(0));

		assertEquals("Translating to upper", "5.5", f.apply(new Double(5.5)).get(0));
		assertEquals("Translating to upper", "5", f.apply(new Double(5)).get(0));
	}
}
