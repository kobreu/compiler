package edu.tum.lua;

import java.util.List;

import edu.tum.lua.ast.Block;

public class LuaInterpreter {

	public static List<Object> eval(Block block, LocalEnvironment environment) {
		BlockVisitor blockVisitor = new BlockVisitor(environment);
		blockVisitor.visit(block);
		return blockVisitor.getReturn();
	}

}
