package edu.tum.juna.junit;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.parser.exception.SyntaxError;

public class LuaReferenceSuite {

	List<String> blacklisted = Arrays.asList("events.lua", "closure.lua", "all.lua");

	@Test
	public void test() throws Exception {
		for (File file : new File("../Frontend/testinput/lua5.1-tests/").listFiles()) {

			if (!file.getName().endsWith(".lua") || blacklisted.contains(file.getName())) {
				continue;
			}

			try {
				Block block = ParserUtil.loadFile(file);
				GlobalEnvironment ge = new GlobalEnvironment();
				ge.getLuaTable("package").set("path",
						ge.getLuaTable("package").get("path") + ";../Frontend/testinput/lua5.1-tests/?.lua");
				LuaInterpreter.eval(block, ge);
			} catch (LuaRuntimeException ex) {
				System.out.println("LuaRuntimeException in: " + file.getName());
				// (new PrettyPrinter()).print(ex);
				throw ex;
			} catch (SyntaxError ex) {
				System.out.println("SyntaxError: " + file.getName());
			}

		}
	}
}
