package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class RawGet extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.TABLE }, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("rawget", arguments, expectedTypes);

		LuaTable table = (LuaTable) arguments.get(0);
		Object key = arguments.get(1);

		return Collections.singletonList(table.rawget(key));
	}

}
