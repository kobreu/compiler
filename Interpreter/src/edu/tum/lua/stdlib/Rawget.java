package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Rawget extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.TABLE }, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		LinkedList<Object> result = new LinkedList<Object>();
		Preconditions.checkArguments("rawget", arguments, expectedTypes);

		LuaTable table = (LuaTable) arguments.get(0);
		result.add(table.get(arguments.get(1)));

		return result;
	}

}
