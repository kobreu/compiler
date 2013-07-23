package edu.tum.lua;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.List;

import edu.tum.lua.ast.Chunk;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.Stat;

public class LuaInterpreter {

	public static List<Object> eval(Chunk chunk) {
		return eval(chunk, new Environment());
	}

	@SuppressWarnings("unused")
	public static List<Object> eval(Chunk chunk, Environment environment) {
		for (Stat statement : convert(chunk.stats)) {
			// TODO
		}

		return null;
	}

	@SuppressWarnings("unused")
	public static Object eval(Exp exp, Environment environment) {

		return false;
	}

}
