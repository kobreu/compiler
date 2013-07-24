package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Rawequal extends LuaFunctionNative {

	LuaType[][] expectedTypes = { null, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		LinkedList<Object> result = new LinkedList<Object>();
		Preconditions.checkArguments("rawequal", arguments, expectedTypes);
		result.add(arguments.get(0).equals(arguments.get(1)));

		return result;
	}

}
