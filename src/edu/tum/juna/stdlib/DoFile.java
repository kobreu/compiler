package edu.tum.juna.stdlib;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.Preconditions;
import edu.tum.juna.ast.Block;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.parser.exception.SyntaxError;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class DoFile extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING } };
	private final GlobalEnvironment globalEnvironment;

	public DoFile(GlobalEnvironment ge) {
		this.globalEnvironment = ge;
	}

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("dofile", arguments, expectedTypes);
		String file = (String) arguments.get(0);

		int lastSlash = file.lastIndexOf("/");
		String prefix = file.substring(0, lastSlash + 1);
		String suffix = file.substring(lastSlash + 1);
		String[] filename = suffix.split("\\.");
		StringBuilder location = new StringBuilder();
		location.append(prefix).append("?.").append(filename[1]);

		globalEnvironment.getLuaTable("package").set("path",
				globalEnvironment.getLuaTable("package").get("path") + ";" + location.toString());

		try {
			Block block = ParserUtil.loadFile(file);
			return LuaInterpreter.eval(block, globalEnvironment);
		} catch (IOException e) {
			System.out.println("Cannot open " + file + ": " + e.getMessage());
		} catch (SyntaxError se) {
			System.out.println("Syntax error in file " + file);
			se.printStackTrace();
		} catch (LuaRuntimeException e) {
			throw e;
		}

		return Collections.emptyList();
	}

}
