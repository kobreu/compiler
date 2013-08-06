package edu.tum.lua.stdlib.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Sub extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER, LuaType.STRING }, { LuaType.NUMBER }, { null, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.sub", arguments, expectedTypes);

		if (LuaType.getTypeOf(arguments.get(0)) == LuaType.NUMBER) {
			arguments.set(0, ToString.toString(arguments.get(0)));
		}

		String s = (String) arguments.get(0);
		int i = (int) Math.floor(((Double) arguments.get(1)).doubleValue()) - 1;
		int j = s.length();
		if (arguments.size() >= 3) {
			j = (int) Math.floor(((Double) arguments.get(2)).doubleValue());
		}

		if (i > s.length()) {
			return Collections.singletonList((Object) "");
		}

		if (j > s.length()) {
			j = s.length();
		}

		if (j < 0) {
			j += s.length() + 1;
		}

		if (i == -1) {
			i = 0;
		}

		if (i < 0) {
			i += s.length() + 1;
			s += s;
		}

		if (i >= 0 && j >= 0) {
			return Arrays.asList(new Object[] { s.substring(i, j) });
		}

		return Collections.singletonList((Object) "");
	}
}