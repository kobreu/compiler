package edu.tum.juna.stdlib.string;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Reverse extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.reverse", arguments, expectedTypes);

		Object o = arguments.get(0);

		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			return apply(new ToString().apply(o));
		}

		return Arrays.asList((Object) new StringBuffer((String) o).reverse().toString());
	}
}
