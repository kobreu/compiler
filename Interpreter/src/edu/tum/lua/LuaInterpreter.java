package edu.tum.lua;

import java.util.List;

import edu.tum.lua.ast.Block;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.exceptions.PrettyPrinter;

public class LuaInterpreter {

	public static List<Object> eval(Block block, GlobalEnvironment environment) {
		try {
			LocalEnvironment localEnv = new LocalEnvironment(environment);
			BlockVisitor blockVisitor = new BlockVisitor(localEnv);
			blockVisitor.visit(block);
			return blockVisitor.getReturn();
		} catch (LuaRuntimeException e) {

			PrettyPrinter.print(e);

		}
		return null;
	}

	public static List<Object> eval(Block block, LocalEnvironment environment) {
		try {
			BlockVisitor blockVisitor = new BlockVisitor(environment);
			blockVisitor.visit(block);
			return blockVisitor.getReturn();
		} catch (LuaRuntimeException e) {
			PrettyPrinter.print(e);
		}

		return null;
	}

}
