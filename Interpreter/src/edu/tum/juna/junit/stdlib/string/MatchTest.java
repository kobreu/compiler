package edu.tum.juna.junit.stdlib.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.tum.juna.stdlib.string.Match;

public class MatchTest {

	@Test
	public void test() {
		Match f = new Match();

		Object[] back;

		back = f.apply("Hello", "%u").toArray();
		assertEquals("H", back[0]);

		back = f.apply("Hello", "%l").toArray();
		assertEquals("e", back[0]);

		back = f.apply("Hello", "%l+").toArray();
		assertEquals("ello", back[0]);

		back = f.apply("Hello", "ll").toArray();
		assertEquals("ll", back[0]);

		String s = "Hello my World,\nmy name is ...";

		back = f.apply(s, "%u[%l ]*%u").toArray();
		assertEquals("Hello my W", back[0]);

		back = f.apply(s, "Moon").toArray();
		assertTrue(back[0] == null);

		back = f.apply(s, "%u[%l%s]*%u[^%s%p]*%p").toArray();
		assertEquals("Hello my World,", back[0]);

		back = f.apply(s, "%d[%l%s]*%u[^%s%p]*%p").toArray();
		assertTrue(back[0] == null);

		back = f.apply(s, "my").toArray();
		assertEquals("my", back[0]);

		back = f.apply(s, "my", 10.0).toArray();
		assertEquals("my", back[0]);

		back = f.apply(5.5, ".").toArray();
		assertEquals("5", back[0]);

		back = f.apply(50.0, 0.0).toArray();
		assertEquals("0", back[0]);

		back = f.apply(50.0, 6.0).toArray();
		assertEquals(null, back[0]);
	}
}
