package edu.tum.juna.junit.stdlib.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.juna.stdlib.string.Format;

public class FormatTest {

	@Test
	public void test() {
		Format f = new Format();

		assertEquals("Hello Lua User!", f.apply("%s %s", "Hello", "Lua User!").get(0));
		assertEquals("Lua", f.apply("%c%c%c", 76.0, 117.0, 97.0).get(0));
		assertEquals("3.141593, 3.14159", f.apply("%f, %g", Math.PI, Math.PI).get(0));
		assertEquals("-100, -100, 4294967196", f.apply("%d, %i, %u", -100.0, -100.0, -100.0).get(0));
		assertEquals("37777777634, ffffff9c, FFFFFF9C", f.apply("%o, %x, %X", -100.0, -100.0, -100.0).get(0));
	}
}
