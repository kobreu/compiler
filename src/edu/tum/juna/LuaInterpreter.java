package edu.tum.juna;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import edu.tum.juna.ast.Block;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.exceptions.LuaStackTraceElement;
import edu.tum.juna.parser.ParserUtil;

public class LuaInterpreter {

	public static List<Object> eval(Block block, GlobalEnvironment environment) {
		LocalEnvironment localEnv = new LocalEnvironment(environment);
		return eval(block, localEnv);
	}

	public static List<Object> eval(Block block, LocalEnvironment environment) {
		BlockVisitor blockVisitor = new BlockVisitor(environment);
		try {
			blockVisitor.visit(block);
		} catch (LuaRuntimeException ex) {
			ex.offerSyntaxNode(block);

			ex.addLuaStackTraceElement(new LuaStackTraceElement(block, "main chunk", Collections.emptyList()));
			throw ex;
		}

		List<Object> ret = blockVisitor.getReturn();

		if (ret == null) {
			return Collections.emptyList();
		}

		return blockVisitor.getReturn();
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		Block block = ParserUtil.loadFile("../Frontend/testinput/homework/salomon_2sat/runSelfCheck.lua");
		GlobalEnvironment ge = new GlobalEnvironment();
		ge.getLuaTable("package").set("path",
				ge.getLuaTable("package").get("path") + ";../Frontend/testinput/homework/salomon_2sat/?.lua");
		eval(block, ge);
	}
}
