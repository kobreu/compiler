package edu.tum.lua;

import java.io.FileNotFoundException;
import java.util.List;

import util.ParserUtil;
import edu.tum.lua.ast.Block;

public class LuaInterpreter {

	public static List<Object> eval(Block block, GlobalEnvironment environment) {
		LocalEnvironment localEnv = new LocalEnvironment(environment);
		BlockVisitor blockVisitor = new BlockVisitor(localEnv);
		blockVisitor.visit(block);
		return blockVisitor.getReturn();
	}

	public static List<Object> eval(Block block, LocalEnvironment environment) {
		BlockVisitor blockVisitor = new BlockVisitor(environment);
		blockVisitor.visit(block);
		return blockVisitor.getReturn();
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/matthias_knapsack.lua");
		eval(block, new GlobalEnvironment());
	}
}
