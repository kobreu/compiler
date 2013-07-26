package edu.tum.lua.types;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LocalEnvironment;
import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.FunctionDef;

public class LuaFunctionInterpreted implements LuaFunction {

	private final LocalEnvironment environment;
	private final List<String> argumentNames;
	private final Block block;

	public LuaFunctionInterpreted(FunctionDef node, LocalEnvironment e) {
		environment = e;
		argumentNames = convert(node.args);
		block = node.block;
	}

	@Override
	public List<Object> apply(List<Object> arguments) {
		LocalEnvironment currentEnvironment = new LocalEnvironment(environment);

		for (int i = 0; i < Math.min(argumentNames.size(), arguments.size()); i++) {
			currentEnvironment.set(argumentNames.get(i), arguments.get(i));
		}

		return LuaInterpreter.eval(block, currentEnvironment);
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
