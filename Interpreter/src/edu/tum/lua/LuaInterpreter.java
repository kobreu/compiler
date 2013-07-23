package edu.tum.lua;

import java.util.List;

import edu.tum.lua.ast.Chunk;
import edu.tum.lua.ast.Expression;
import edu.tum.lua.ast.Statement;

public class LuaInterpreter {

	public static List<Object> eval(Chunk chunk) {
		return eval(chunk, new Environment());
	}

	public static List<Object> eval(Chunk chunk, Environment environment) {
		for (Statement statement : chunk.getStats()) {
			switch (statement.getType()) {
			case BAR:
				break;

			case RETURN:
				return null;

			case ASSIGN:
				environment.get("foo");
				environment.set("foo", "bar");
				break;

			case FOO:
				break;

			default:
				break;
			}
		}

		return null;
	}

	@SuppressWarnings("unused")
	public static Object eval(Expression exp, Environment environment) {
		return null;
	}

}
