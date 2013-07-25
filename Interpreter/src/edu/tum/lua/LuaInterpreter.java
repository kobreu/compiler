package edu.tum.lua;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.List;

import edu.tum.lua.ast.Chunk;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.FunctionCall;
import edu.tum.lua.ast.Stat;

public class LuaInterpreter {

	public static List<Object> eval(Chunk chunk) {
		return eval(chunk, new Environment());
	}

	public static List<Object> eval(Chunk chunk, Environment environment) {
		StatementVisitor visitor = new StatementVisitor(environment);

		for (Stat statement : convert(chunk.stats)) {
			statement.accept(visitor);
		}

		return null;
	}

	public static Object eval(Exp exp, Environment environment) {
		ExpVisitor visitor = new ExpVisitor(environment);
		exp.accept(visitor);
		return visitor.popLast();
	}

	public static void eval(FunctionCall call, Environment environment2) {
		throw new RuntimeException("Missing implementation");
	}

}
