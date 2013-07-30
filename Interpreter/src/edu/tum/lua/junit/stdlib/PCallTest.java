package edu.tum.lua.junit.stdlib;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import edu.tum.lua.stdlib.NotImplementedFunction;
import edu.tum.lua.stdlib.PCall;
import edu.tum.lua.stdlib.VoidFunction;

public class PCallTest {

	@Test
	public void test() {
		PCall pCall = new PCall();

		assertEquals(Collections.singletonList(Boolean.TRUE), pCall.apply(new VoidFunction()));
		assertEquals(Arrays.asList(Boolean.FALSE, "Not yet implemented"), pCall.apply(new NotImplementedFunction()));
	}
}
