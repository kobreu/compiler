package edu.tum.lua;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.FunctionCall;
import edu.tum.lua.ast.LastBreak;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.Stat;

public class LuaInterpreter {

	public static List<Object> eval(Block block) {
		return eval(block, new Environment());
	}

	public static List<Object> eval(Block block, Environment environment) {
		StatementVisitor visitor = new StatementVisitor(environment);

		for (Stat statement : convert(block.stats)) {
			statement.accept(visitor);
		}

		if (block.last == null || block.last instanceof LastBreak) {
			return Collections.emptyList();
		}

		/* LastReturn */
		Environment lastEnvironment = visitor.getEnvironment();
		return eval(((LastReturn) block.last).explist, lastEnvironment);
	}

	public static List<Object> eval(ExpList explist, Environment environment) {
		throw new RuntimeException("Missing implementation");
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
