package edu.tum.juna.junit.stdlib.table;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.stdlib.table.MaxN;
import edu.tum.juna.types.LuaTable;

public class MaxNTest {

	private MaxN maxN;

	@Before
	public void setUp() throws Exception {
		maxN = new MaxN();
	}

	@Test
	public void testApplyListOfObject() {
		LuaTable table = new LuaTable();

		assertEquals(1, maxN.apply(table).size());
		assertEquals(0.0, maxN.apply(table).get(0));
		table.set("a", "a");
		assertEquals(0.0, maxN.apply(table).get(0));
		table.set(1.2, "a");
		assertEquals(1.2, maxN.apply(table).get(0));
		table.set(-1.0, "a");
		assertEquals(1.2, maxN.apply(table).get(0));
		table.set(1.2, null);
		assertEquals(0.0, maxN.apply(table).get(0));
	}

	@Test(expected = LuaBadArgumentException.class)
	public void testNoArgument() {
		maxN.apply();
	}

	@Test(expected = LuaBadArgumentException.class)
	public void testFirstBadArgument() {
		maxN.apply(1.0);
	}
}
