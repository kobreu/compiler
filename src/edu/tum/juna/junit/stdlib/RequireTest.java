package edu.tum.juna.junit.stdlib;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.exceptions.LuaIOException;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.types.LuaType;

public class RequireTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private GlobalEnvironment globalEnvironment;

	@Before
	public void setUp() throws Exception {
		globalEnvironment = new GlobalEnvironment();
	}

	@Test
	public void testRequire() throws Exception {
		// Create mymodule.lua
		File file = folder.newFile("mymodule.lua");

		try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
			out.write("t={} \n");
			out.write("return t \n");
			out.close();
		}

		// Push the temporary folder on the path
		String path = globalEnvironment.getLuaTable("package").getString("path");
		String fileAbsolutePath = file.getAbsolutePath();
		String filePath = fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf(File.separator));
		globalEnvironment.getLuaTable("package").set("path", path + ";" + filePath + "/?.lua");

		Block block1 = ParserUtil.loadString("m=require(\"mymodule\")");
		Block block2 = ParserUtil.loadString("n=2");

		assertEquals(null, globalEnvironment.get("m"));
		LuaInterpreter.eval(block1, globalEnvironment);
		LuaInterpreter.eval(block2, globalEnvironment);
		assertEquals(LuaType.TABLE, LuaType.getTypeOf(globalEnvironment.get("m")));
		assertEquals(LuaType.TABLE,
				LuaType.getTypeOf(globalEnvironment.getLuaTable("package").getLuaTable("loaded").get("mymodule")));

	}

	@Test(expected = LuaIOException.class)
	public void testRequire2() throws Exception {
		Block block1 = ParserUtil.loadString("m=require(\"notexisting\")");
		LuaInterpreter.eval(block1, globalEnvironment);
	}
}
