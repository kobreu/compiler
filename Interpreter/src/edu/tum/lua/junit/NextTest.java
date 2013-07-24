package edu.tum.lua.junit;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class NextTest {
	/* Test the next(table [,index]) function of the Lua standard lib */

	// TODO write Unit tests
	// > next() >> exception
	// > next({}) >> nil
	// > next({[1] = 4}) >> 1
	// > next({[1] = 4}, nil) >> 1,4
	// > next({[1] = 4}, 1) >> nil
	// > next({[1] = 4, [2] = 5}, nil) >> 1,4 or 2,5
	// > next({[1] = 4, [2] = 5}, 1) >> 2,5

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApplyListOfObject() {
		fail("Not yet implemented");
	}

}
