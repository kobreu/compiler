package edu.tum.juna.stdlib;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class GetMetatable extends LuaFunctionNative {

	private static final LuaType[][] types = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("getmetatable", arguments, types);
		LuaTable table = (LuaTable) arguments.get(0);
		return Collections.singletonList((Object) table.getMetatable());
	}
}
