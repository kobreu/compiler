package edu.tum.lua.stdlib.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Byte extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER }, { null, LuaType.NUMBER },
			{ null, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.byte", arguments, expectedTypes);

		if (LuaType.getTypeOf(arguments.get(0)) == LuaType.NUMBER) {
			arguments.set(0, ToString.toString(arguments.get(0)));
		}

		String s = (String) arguments.get(0);
		int i = 0, j = 1;

		if (arguments.size() >= 2) {
			i = (int) Math.rint(((Double) arguments.get(1)).doubleValue()) - 1;
			j = i + 1;

			if (arguments.size() >= 3) {
				j = (int) Math.rint(((Double) arguments.get(2)).doubleValue());
			}
		}

		if (i > j || i > s.length()) {
			return Collections.emptyList();
		}

		if (j > s.length()) {
			j = s.length();
		}

		if (i == -1) {
			i = 0;
		}

		if (i < -1) {

			i += s.length() + 1;
			j += s.length() + 1;
			s += s;
		}

		if (i >= 0 && j >= 0) {
			byte[] b = s.substring(i, j).getBytes();
			Object[] d = new Object[b.length];

			for (int n = 0; n < b.length; n++) {
				d[n] = new Double(b[n]);
			}

			return Arrays.asList(d);
		} else {
			return Collections.emptyList();
		}
	}
}
