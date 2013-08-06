package edu.tum.juna.stdlib;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class RawGet extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.TABLE }, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("rawget", arguments, expectedTypes);

		LuaTable table = (LuaTable) arguments.get(0);
		Object key = arguments.get(1);

		return Collections.singletonList(table.rawget(key));
	}

}
