package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class RawSet extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.TABLE }, null, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("rawset", arguments, expectedTypes);

		LuaTable table = (LuaTable) arguments.get(0);
		Object key = arguments.get(1);
		Object value = arguments.get(2);

		table.rawset(key, value);

		return Collections.singletonList((Object) table);
	}

}
