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

		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/matthias_knapsack_TMP.lua");
		LuaInterpreter.eval(block, environment);

		fail("Not yet implemented");
	}

	@Test
	public void testJohannes() throws FileNotFoundException, Exception {
		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/johannes_parser_test.lua");
		LuaInterpreter.eval(block, environment);
	}

	@Test
	public void testLisa() throws FileNotFoundException, Exception {
		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/lisa_avltree/avltree_test.lua");
		LuaInterpreter.eval(block, environment);
		System.out.println("Minitest - Lisa");
		block = ParserUtil.loadFile("../Frontend/testinput/homework/lisa_avltree/avltree.lua");
		LuaInterpreter.eval(block, environment);

		fail("Not yet implemented");
	}

}
