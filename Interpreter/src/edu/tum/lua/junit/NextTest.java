package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;

public class NextTest {
	/* Test the next(table [,index]) function of the Lua standard lib */

	// TODO write Unit tests
	// > next() >> exception

	LuaFunctionNative next;
	LuaTable testTable;

	@Test
	public void testApplyListOfObject() {

		// > next({}) >> nil
		assertEquals(null, next.apply(new LuaTable()));

		// > next({[1] = 4}) >> 1, 4
		testTable = new LuaTable();
		testTable.set(1.0, 4.0);
		assertEquals(Arrays.asList(1.0, 4.0), next.apply(testTable));

		// > next({[1] = 4}, nil) >> 1,4
		assertEquals(Arrays.asList(1.0, 4.0), next.apply(testTable, null));

		// > next({[1] = 4}, 1) >> nil
		assertEquals(null, next.apply(testTable, 1.0));

		// > next({[1] = 4, [2] = 5}, nil) >> 1,4 or 2,5
		// TODO

		// > next({[1] = 4, [2] = 5}, 1) >> 2,5 or nil
		testTable.set(2.0, 5.0);
		// TODO

		fail("Not yet implemented");
	}
}
