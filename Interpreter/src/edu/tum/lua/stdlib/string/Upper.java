package edu.tum.lua.stdlib.string;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Upper extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.upper", arguments, expectedTypes);

		Object o = arguments.get(0);

		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			return apply(new ToString().apply(o));
		}

		return Arrays.asList((Object) ((String) o).toUpperCase());
	}
}
