package edu.tum.juna.stdlib.string;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Rep extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER }, { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.rep", arguments, expectedTypes);

		Object o = arguments.get(0);
		Object b = arguments.get(1);

		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			return apply(ToString.toString(o), b);
		}

		String result = "";

		for (int i = 0; i < Math.rint(((Double) b).doubleValue()); i++) {
			result += (String) o;
		}

		return Arrays.asList((Object) result);
	}
}
