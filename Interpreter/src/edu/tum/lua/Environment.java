package edu.tum.lua;

import java.util.ArrayList;
import java.util.List;

import edu.tum.lua.ast.Expression;
import edu.tum.lua.types.LuaTable;

public class Environment extends LuaTable {

	private final static Environment _G = new Environment();

	public static Environment getGlobalEnvironment() {
		return _G;
	}

	Environment() {
		set("_G", this);
	}

	public Environment(Environment forward) {
		super(forward);
	}

	public void assign(List<String> identifiers, List<Expression> expressions) {
		List<Object> values = new ArrayList<>(expressions.size());

		for (Expression exp : expressions) {
			values.add(LuaInterpreter.eval(exp, this));
		}

		for (int i = 0; i < identifiers.size(); i++) {
			set(identifiers.get(i), values.get(i));
		}
	}
}
