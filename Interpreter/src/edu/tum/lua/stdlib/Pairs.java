package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Pairs extends LuaFunctionNative {
	/*
	 * pairs(table) returns three values: the next function, the table t, and
	 * nil.
	 */

	LuaType[][] expectedTypes = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {

		checkArguments("pairs", arguments, expectedTypes);

		Next nextFunction = new Next();
		LuaTable table = (LuaTable) arguments.get(0);

		return Arrays.asList(nextFunction, table, null);
	}
}
