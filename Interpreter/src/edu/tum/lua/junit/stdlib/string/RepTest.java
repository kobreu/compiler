package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.lua.stdlib.string.Rep;

public class RepTest {

	@Test
	public void test() {
		Rep f = new Rep();

		assertEquals("aaa", f.apply("a", new Double(3)).get(0));
		assertEquals("", f.apply("a", new Double(0)).get(0));
		assertEquals("", f.apply("", new Double(3)).get(0));

		assertEquals("5555", f.apply(new Double(5), new Double(4)).get(0));
		assertEquals("5.45.45.4", f.apply(new Double(5.4), new Double(3.499)).get(0));
		assertEquals("5.45.45.45.4", f.apply(new Double(5.4), new Double(3.5)).get(0));
	}
}
