package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.tum.lua.stdlib.string.Lower;

public class LowerTest {

	@Test
	public void test() {
		Lower f = new Lower();

		assertEquals("Translating to lower", "hello world", f.apply("Hello World").get(0));
		assertEquals("Translating to lower", "hello world", f.apply("hello world").get(0));
		assertEquals("Translating to lower", "hello world", f.apply("HELLO WORLD").get(0));

		assertEquals("Translating to lower", "5.5", f.apply(new Double(5.5)).get(0));
		assertEquals("Translating to lower", "5", f.apply(new Double(5)).get(0));
	}
}
