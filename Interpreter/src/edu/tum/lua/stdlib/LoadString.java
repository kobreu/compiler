package edu.tum.lua.stdlib;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.Environment;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.Parser;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class LoadString extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("loadstring", arguments, expectedTypes);
		try {
			FunctionDef node = Parser.parseString(arguments.get(0).toString());
			LuaFunctionInterpreted function = new LuaFunctionInterpreted(node, Environment.getGlobalEnvironment());
			return Collections.singletonList((Object) function);
		} catch (LuaRuntimeException e) {
			return Collections.singletonList(null);
		}
	}

}
