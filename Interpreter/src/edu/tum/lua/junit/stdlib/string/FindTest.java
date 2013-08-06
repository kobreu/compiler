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

		res = Find.getIndexOfPattern("Hello", "%u");
		assertTrue(res[0] == 0 && res[1] == 0);

		res = Find.getIndexOfPattern("Hello", "%l");
		assertTrue(res[0] == 1 && res[1] == 1);

		res = Find.getIndexOfPattern("Hello", "%l+");
		assertTrue(res[0] == 1 && res[1] == 4);

		res = Find.getIndexOfPattern("Hello", "ll");
		assertTrue(res[0] == 2 && res[1] == 3);

		String s = "Hello my World,\nmy name is ...";

		res = Find.getIndexOfPattern(s, "%u[%l ]*%u");
		assertTrue(res[0] == 0 && res[1] == 9);

		res = Find.getIndexOfPattern(s, "%u[%l%s]*%u[^%s%p]*%p");
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

		s = "Hello (World)!";
		res = Find.getIndexOfPattern(s, "%([^%(%)]*%)");
		assertTrue(res[0] == 6 && res[1] == 12);

		back = f.apply(5.5, ".").toArray();
		assertTrue(((Double) back[0]).intValue() == 1 && ((Double) back[1]).intValue() == 1);

		back = f.apply(50.0, 0.0).toArray();
		assertTrue(((Double) back[0]).intValue() == 2 && ((Double) back[1]).intValue() == 2);

		back = f.apply(50.0, 6.0).toArray();
		assertTrue(back[0] == null);
	}

	private void checkPatternConvertion() {
		assertEquals("[a-zA-Z]", Find.convertPattern("%a"));
		assertEquals("[a-zA-Z]*", Find.convertPattern("%a*"));
		assertEquals("\\p{Alnum}", Find.convertPattern("%w"));
		assertEquals("[\\p{XDigit}]+", Find.convertPattern("[%x]+"));
		assertEquals("[^\\p{Upper}]?", Find.convertPattern("[^%u]?"));
		assertEquals("[[^\\p{Upper}]]?", Find.convertPattern("[%U]?"));
		assertEquals("[\\+\\-\\*/]?", Find.convertPattern("[%+%-%*/]?"));
		assertEquals("[\\{\\(\\)\\}]", Find.convertPattern("[{%(%)}]"));

		try {
			Find.convertPattern("%z");
			fail();
		} catch (Exception e) {

		}

		try {
			System.out.println(Find.convertPattern("^[\\w*%z]?"));
			fail();
		} catch (Exception e) {

		}

		try {
			System.out.println(Find.convertPattern("%a-"));
			fail();
		} catch (Exception e) {

		}

		try {
			System.out.println(Find.convertPattern("[^%a]-"));
			fail();
		} catch (Exception e) {

		}

		try {
			Find.convertPattern("[a-z]([^%a]-)");
			fail();
		} catch (Exception e) {

		}

		try {
			Find.convertPattern("[%a%5]+?");
			fail("");
		} catch (Exception e) {

		}

		try {
			Find.convertPattern("[a-zA-Z0-0_\\(\\)]*%b().*");
			fail("");
		} catch (Exception e) {

		}

		try {
			Find.convertPattern("[^z]?");
			Find.convertPattern("[a-z]?");
		} catch (Exception e) {
			fail("");
		}
	}
}
