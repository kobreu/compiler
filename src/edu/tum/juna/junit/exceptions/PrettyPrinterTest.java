package edu.tum.juna.junit.exceptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.exceptions.PrettyPrinter;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.parser.exception.SyntaxError;

public class PrettyPrinterTest {

	@Test
	public void testPrint() throws SyntaxError, IOException {
		Map<Block, File> map = new HashMap<>();

		File crash = new File("testinput/crash.lua");
		Block block = ParserUtil.loadFile(crash);

		map.put(block, crash);
		PrettyPrinter pp = new PrettyPrinter();
		try {
			LuaInterpreter.eval(block, new GlobalEnvironment());
		} catch (LuaRuntimeException ex) {
			pp.print(ex);
		}
	}
}
