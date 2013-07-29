package edu.tum.lua.types;

import static edu.tum.lua.ast.LegacyAdapter.convert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LocalEnvironment;
import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.Name;

public class LuaFunctionInterpreted implements LuaFunction {

	private final LocalEnvironment environment;
	private final List<String> argumentNames;
	private final Block block;
	private final boolean varargs;

	public LuaFunctionInterpreted(FunctionDef node, LocalEnvironment e) {
		environment = e;
		argumentNames = new LinkedList<String>();
		List<Name> l = convert(node.args);
		for (Name argName : l) {
			argumentNames.add(argName.name);
		}
		block = node.block;
		varargs = node.varargs;
	}

	public LuaFunctionInterpreted(LocalFuncDef node, LocalEnvironment e) {
		environment = e;
		argumentNames = new LinkedList<String>();
		List<Name> l = convert(node.args);
		for (Name argName : l) {
			argumentNames.add(argName.name);
		}
		block = node.block;
		varargs = node.varargs;
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
