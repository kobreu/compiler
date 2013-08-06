package edu.tum.juna.stdlib;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class SetMetatable extends LuaFunctionNative {

	private static final LuaType[][] types = { { LuaType.TABLE }, { LuaType.TABLE, LuaType.NIL } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("setmetatable", arguments, types);

		LuaTable table = (LuaTable) arguments.get(0);
		LuaTable metaTable = (LuaTable) arguments.get(1);

		table.setMetatable(metaTable);

		return Collections.singletonList((Object) table);
	}
}
