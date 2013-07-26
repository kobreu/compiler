package edu.tum.lua.junit.stdlib.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.tum.lua.stdlib.string.Find;

public class FindTest {

	@Test
	public void test() {
		checkPatternConvertion();

		Find f = new Find();

		int[] res;

		res = f.getIndexOfPattern("Hello", "%u");
		assertTrue(res[0] == 0 && res[1] == 0);

		res = f.getIndexOfPattern("Hello", "%l");
		assertTrue(res[0] == 1 && res[1] == 1);

		res = f.getIndexOfPattern("Hello", "%l+");
		assertTrue(res[0] == 1 && res[1] == 4);

		res = f.getIndexOfPattern("Hello", "ll");
		assertTrue(res[0] == 2 && res[1] == 3);

		String s = "Hello my World,\nmy name is ...";

		res = f.getIndexOfPattern(s, "%u[%l ]*%u");
		assertTrue(res[0] == 0 && res[1] == 9);

		res = f.getIndexOfPattern(s, "%u[%l%s]*%u[^%s%p]*%p");
		assertTrue(res[0] == 0 && res[1] == 14);

		Object[] back = f.apply(s, "Moon").toArray();
		assertTrue(back[0] == null);

		back = f.apply(s, "%u[%l%s]*%u[^%s%p]*%p").toArray();
		assertTrue(((Double) back[0]).intValue() == 1 && ((Double) back[1]).intValue() == 15);

		back = f.apply(s, "%d[%l%s]*%u[^%s%p]*%p").toArray();
		assertTrue(back[0] == null);

		back = f.apply(s, "my").toArray();
		assertTrue(((Double) back[0]).intValue() == 7 && ((Double) back[1]).intValue() == 8);

		back = f.apply(s, "my", 10.0).toArray();
		assertTrue(((Double) back[0]).intValue() == 17 && ((Double) back[1]).intValue() == 18);

		back = f.apply(s, "my", 1.0, new Boolean(true)).toArray();
		assertTrue(((Double) back[0]).intValue() == 7 && ((Double) back[1]).intValue() == 8);

		back = f.apply(s, "my", 10.0, new Boolean(true)).toArray();
		assertTrue(((Double) back[0]).intValue() == 17 && ((Double) back[1]).intValue() == 18);

		back = f.apply(5.5, ".").toArray();
		assertTrue(((Double) back[0]).intValue() == 1 && ((Double) back[1]).intValue() == 1);

		back = f.apply(50.0, 0.0).toArray();
		assertTrue(((Double) back[0]).intValue() == 2 && ((Double) back[1]).intValue() == 2);

		back = f.apply(50.0, 6.0).toArray();
		assertTrue(back[0] == null);
	}

	private void checkPatternConvertion() {
		Find f = new Find();

		assertEquals("", f.convertPattern(""));
		assertEquals("[a-zA-Z]", f.convertPattern("%a"));
		assertEquals("[a-zA-Z]*", f.convertPattern("%a*"));
		assertEquals("\\p{Alnum}", f.convertPattern("%w"));
		assertEquals("[\\p{XDigit}]+", f.convertPattern("[%x]+"));
		assertEquals("[^\\p{Upper}]?", f.convertPattern("[^%u]?"));

		try {
			f.convertPattern("%z");
			fail();
		} catch (Exception e) {

		}

		try {
			System.out.println(f.convertPattern("^[\\w*%z]?"));
			fail();
		} catch (Exception e) {

		}

		try {
			System.out.println(f.convertPattern("%a-"));
			fail();
		} catch (Exception e) {

		}

		try {
			System.out.println(f.convertPattern("[^%a]-"));
			fail();
		} catch (Exception e) {

		}

		try {
			f.convertPattern("[a-z]([^%a]-)");
			fail();
		} catch (Exception e) {

		}

		try {
			f.convertPattern("[%a%5]+?");
			fail("");
		} catch (Exception e) {

		}

		try {
			f.convertPattern("[a-zA-Z0-0_\\(\\)]*%b().*");
			fail("");
		} catch (Exception e) {

		}

		try {
			f.convertPattern("[^z]?");
			f.convertPattern("[a-z]?");
		} catch (Exception e) {
			fail("");
		}
	}
}
