package edu.tum.lua.types;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.ExpVisitor;
import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LocalEnvironment;
import edu.tum.lua.StatementVisitor;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.LastBreak;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.LegacyAdapter;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.Stat;

public class LuaFunctionInterpreted implements LuaFunction {

	private final LocalEnvironment environment;
	private final List<String> argumentNames;
	private final Block block;
	private final boolean vararg;

	public LuaFunctionInterpreted(List<String> args, boolean vararg, Block block, LocalEnvironment e) {
		this.argumentNames = args;
		this.vararg = vararg;
		this.block = block;
		this.environment = e;
	}

	public LuaFunctionInterpreted(FunctionDef node, LocalEnvironment e) {
		environment = e;
		argumentNames = LegacyAdapter.convert(node.args);
		block = node.block;
		vararg = node.varargs;
	}

	public LuaFunctionInterpreted(LocalFuncDef node, LocalEnvironment e) {
		environment = e;
		argumentNames = LegacyAdapter.convert(node.args);
		block = node.block;
		vararg = node.varargs;
	}

	@Override
	public List<Object> apply(List<Object> arguments) {
		LocalEnvironment currentEnvironment = new LocalEnvironment(environment);

		for (int i = 0; i < Math.min(argumentNames.size(), arguments.size()); i++) {
			currentEnvironment.setLocal(argumentNames.get(i), arguments.get(i));
		}

		StatementVisitor visitor;

		if (vararg) {
			visitor = new StatementVisitor(currentEnvironment,
					arguments.subList(argumentNames.size(), arguments.size()));
		} else {
			visitor = new StatementVisitor(currentEnvironment);
		}

		for (Stat statement : convert(block.stats)) {
			statement.accept(visitor);
		}

		if (block.last == null || block.last instanceof LastBreak) {
			return Collections.emptyList();
		}

		/* LastReturn */
		LocalEnvironment lastEnvironment = visitor.getEnvironment();
		ExpVisitor visitor2;
		if (vararg) {
			visitor2 = new ExpVisitor(lastEnvironment, arguments.subList(argumentNames.size(), arguments.size()));
		} else {
			visitor2 = new ExpVisitor(lastEnvironment, null);
		}

		((LastReturn) block.last).explist.accept(visitor2);

		return visitor2.popAll();
	}

	@Override
	public List<Object> apply(Object... arguments) {
		return apply(Arrays.asList(arguments));
	}

	public GlobalEnvironment getGlobalEnvironment() {
		return environment.getGlobalEnvironment();
	}

	public void setGlobalEnvironment(GlobalEnvironment e) {
		environment.setGlobalEnvironment(e);
	}
}
