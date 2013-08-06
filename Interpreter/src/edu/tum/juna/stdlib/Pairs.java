package edu.tum.juna.stdlib;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

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
