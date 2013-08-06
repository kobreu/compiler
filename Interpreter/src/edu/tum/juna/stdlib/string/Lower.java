package edu.tum.juna.stdlib.string;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Lower extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.lower", arguments, expectedTypes);

		Object o = arguments.get(0);

		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			return apply(ToString.toString(o));
		}

		return Arrays.asList((Object) ((String) o).toLowerCase());
	}
}
