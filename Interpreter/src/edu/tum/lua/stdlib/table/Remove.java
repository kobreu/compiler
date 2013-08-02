package edu.tum.lua.stdlib.table;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Remove extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("table.remove", arguments, expectedTypes);
		LuaTable table = (LuaTable) arguments.get(0);

		MaxN op = new MaxN();
		double length = (double) op.apply(table).get(0);

		double pos = length;
		if (arguments.size() > 1) {
			if (LuaType.getTypeOf(arguments.get(1)) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(2, "table.remove", "number", LuaType.getTypeOf(arguments.get(1))
						.toString());
			}
			pos = (double) arguments.get(1);
		}
		if (pos > length || pos < 1) {
			return Collections.emptyList();
		}
		Object result = table.get(pos);
		for (double i = pos; i < length + 1; i = i + 1) {
			table.set(i, table.get(i + 1));
		}
		return Collections.singletonList(result);
	}

}
