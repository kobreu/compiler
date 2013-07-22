package edu.tum.lua.types;

import java.util.List;

import edu.tum.lua.ast.FunctionNode;

public class LuaFunction {
	
	private final FunctionNode node;
	private final LuaTable localEnvironment;
	private final List<String> formalParameterNames;
	
	public LuaFunction(FunctionNode l) {
		node = l;
		localEnvironment = new LuaTable();
		formalParameterNames = node.getFormalParameters();
	}
	
	public List<Object> apply(List<Object> arguments) {
		return null;
	}

	public List<Object> apply(Object key) {
		return null;
	}
}
