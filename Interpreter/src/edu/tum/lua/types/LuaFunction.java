package edu.tum.lua.types;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.ast.FunctionNode;

public class LuaFunction {

	private final FunctionNode node;
	private final LuaTable environment;
	private final List<String> formalParameterNames;

	public LuaFunction(LuaTable e, FunctionNode l) {
		node = l;
		environment = e;
		formalParameterNames = node.getFormalParameters();
	}

	public List<Object> apply(List<Object> arguments) {
		LuaTable currentEnvironment = new LuaTable(environment);

		for (int i = 0; i < Math.min(formalParameterNames.size(), arguments.size()); i++) {
			currentEnvironment.set(formalParameterNames.get(i), arguments.get(i));
		}

		return LuaInterpreter.eval(node.getChunk(), currentEnvironment);
	}

	public List<Object> apply(Object... arguments) {
		return apply(Arrays.asList(arguments));
	}
}
