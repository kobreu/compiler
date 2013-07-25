package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CharTest {

	@Test
	public void test() {
		edu.tum.lua.stdlib.string.Char f = new edu.tum.lua.stdlib.string.Char();

		assertEquals("Hello", f.apply(72.0, 101.0, 108.0, 108.0, 111.0).get(0));
		assertEquals("", f.apply().get(0));

		try {
			f.apply(-5.0);
			fail("");
		} catch (Exception e) {

		}

		try {
			f.apply(1000.0);
			fail("");
		} catch (Exception e) {

		}
	}
}
