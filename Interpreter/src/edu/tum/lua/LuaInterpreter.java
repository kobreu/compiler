package edu.tum.lua;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.LastBreak;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.Stat;

public class LuaInterpreter {

	public static List<Object> eval(Block block) {
		return eval(block, new LocalEnvironment());
	}

	public static List<Object> eval(Block block, LocalEnvironment environment) {
		StatementVisitor visitor = new StatementVisitor(environment);

		for (Stat statement : convert(block.stats)) {
			statement.accept(visitor);
		}

		if (block.last == null || block.last instanceof LastBreak) {
			return Collections.emptyList();
		}

		/* LastReturn */
		LocalEnvironment lastEnvironment = visitor.getEnvironment();
		return eval(((LastReturn) block.last).explist, lastEnvironment);
	}

	public static List<Object> eval(ExpList explist, LocalEnvironment environment) {
		throw new RuntimeException("Missing implementation");
	}

	public static Object eval(Exp exp, LocalEnvironment environment) {
		ExpVisitor visitor = new ExpVisitor(environment);
		exp.accept(visitor);
		return visitor.popLast();
	}
}
