package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.tum.lua.stdlib.string.Len;

public class LenTest {

	@Test
	public void test() {
		Len f = new Len();

		assertEquals("Len :: String", 0, ((Double) f.apply("").get(0)).intValue());
		assertEquals("Len :: String", 5, ((Double) f.apply("Hello").get(0)).intValue());
		assertEquals("Len :: String", 5, ((Double) f.apply("a\000bc\000").get(0)).intValue());
		assertEquals("Len :: Double", 1, ((Double) f.apply(new Double(5)).get(0)).intValue());
		assertEquals("Len :: Double", 3, ((Double) f.apply(new Double(5.5)).get(0)).intValue());

		try {
			f.apply(new Boolean(true));
			fail("Len :: Boolean");
		} catch (Exception e) {
			assertTrue("Len :: Boolean", true);
		}

		try {
			f.apply((Object) null);
			fail("Len :: Nil");
		} catch (Exception e) {
			assertTrue("Len :: Nil", true);
		}

	}
}
