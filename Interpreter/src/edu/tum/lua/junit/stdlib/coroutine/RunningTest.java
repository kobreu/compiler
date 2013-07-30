package edu.tum.lua.junit.stdlib.coroutine;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.stdlib.coroutine.Running;

public class RunningTest {

	@Test
	public void test() {
		Running r = new Running();
		assertEquals(null, r.apply(Collections.emptyList()));
	}

}
