package edu.tum.lua.junit.homework;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import util.ParserUtil;
import edu.tum.lua.LocalEnvironment;
import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.ast.Block;

public class HomeworkTest {

	private LocalEnvironment environment;

	@Before
	public void setUp() throws Exception {
		environment = new LocalEnvironment();
	}

	@Test
	public void testMatthias() throws FileNotFoundException, Exception {

		/*
		 * Run the knapsack lua file, expected return value is 32
		 */

		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/matthias_knapsack.lua");
		LuaInterpreter.eval(block, environment);

		fail("Not yet implemented");
	}

}
