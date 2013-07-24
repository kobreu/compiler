package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Rawset extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.TABLE }, null, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("rawset", arguments, expectedTypes);
		LuaTable table = (LuaTable) arguments.get(0);

		if (LuaType.getTypeOf(arguments.get(1)) == LuaType.NIL)
			throw new LuaRuntimeException("table index is nil");
		table.set(arguments.get(1), arguments.get(2));

		LinkedList<Object> result = new LinkedList<Object>();
		result.add(table);
		return result;
	}

}
