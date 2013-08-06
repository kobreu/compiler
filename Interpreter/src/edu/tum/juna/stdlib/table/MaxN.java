package edu.tum.juna.stdlib.table;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class MaxN extends LuaFunctionNative {

	private static final LuaType[][] types = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("maxn", arguments, types);
		LuaTable table = (LuaTable) arguments.get(0);

		double max = 0;

		for (Map.Entry<Object, Object> entry : table) {
			Object key = entry.getKey();

			if (LuaType.getTypeOf(key) == LuaType.NUMBER) {
				max = Math.max(max, (Double) key);
			}
		}

		return Collections.singletonList((Object) max);
	}
}
