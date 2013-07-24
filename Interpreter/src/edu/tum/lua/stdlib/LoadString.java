package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.Environment;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.ast.Function;
import edu.tum.lua.ast.Parser;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class LoadString extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("loadstring", arguments, expectedTypes);
		try {
			Function node = Parser.parseString(arguments.get(0).toString());
			LuaFunctionInterpreted function = new LuaFunctionInterpreted(node, Environment.getGlobalEnvironment());
			LinkedList<Object> result = new LinkedList<Object>();
			result.add(function);
			return result;
		} catch (LuaRuntimeException e) {
			LinkedList<Object> result = new LinkedList<Object>();
			result.add(null);
			return result;
		}
	}

}
