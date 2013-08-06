package edu.tum.juna.junit.homework;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LocalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.parser.ParserUtil;

public class HomeworkTest {

	private LocalEnvironment environment;

	@Before
	public void setUp() throws Exception {
		environment = new LocalEnvironment();
	}

	// @Test
	// public void testIsa() throws FileNotFoundException, Exception {
	// Block block =
	// ParserUtil.loadFile("../Frontend/testinput/homework/isabel_berry_sethi/test.lua");
	// GlobalEnvironment ge = new GlobalEnvironment();
	// ge.getLuaTable("package").set("path",
	// ge.getLuaTable("package").get("path") +
	// ";../Frontend/testinput/homework/isabel_berry_sethi/?.lua");
	//
	// LuaInterpreter.eval(block, ge);
	// System.out.println("======================");
	// }

	@Test
	public void testMatthias() throws FileNotFoundException, Exception {

		/*
		 * Run the knapsack lua file, expected return value is 36
		 */

		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/matthias_knapsack_run.lua");
		LuaInterpreter.eval(block, environment);

		System.out.println("======================");

	}

	@Test
	public void testJohannes() throws FileNotFoundException, Exception {
		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/johannes_parser_run.lua");
		String[] arg = new String[1];
		arg[0] = "(a)";
		GlobalEnvironment ge = new GlobalEnvironment(arg);
		LuaInterpreter.eval(block, ge);

		System.out.println("======================");
	}

	@Test
	public void testLisa() throws FileNotFoundException, Exception {
		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/lisa_avltree/test.lua");
		GlobalEnvironment ge = new GlobalEnvironment();
		ge.getLuaTable("package").set("path",
				ge.getLuaTable("package").get("path") + ";../Frontend/testinput/homework/lisa_avltree/?.lua");
		LuaInterpreter.eval(block, ge);

		System.out.println("======================");
	}

}
