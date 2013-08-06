package edu.tum.lua.stdlib.string;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class GMatch extends LuaFunctionNative {

	private class NextMatch extends LuaFunctionNative {
		LuaType[][] expectedTypes = { { LuaType.STRING }, { LuaType.STRING } };

		public NextMatch() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public List<Object> apply(List<Object> arguments) {
			checkArguments("nextmatch", arguments, expectedTypes);
			String p = (String) arguments.get(0);
			String s = (String) arguments.get(1);

			Object[] index = new Find().apply(s, p).toArray();

			if (index[0] == null) {
				return Collections.singletonList((Object) null);
			}

			String result = s.substring(((Double) index[0]).intValue() - 1, ((Double) index[1]).intValue());

			String rest = s.substring(((Double) index[1]).intValue());

			return Arrays.asList((Object) result, (Object) rest);
		}

	}

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER }, { LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.match", arguments, expectedTypes);

		if (LuaType.getTypeOf(arguments.get(0)) == LuaType.NUMBER) {
			arguments.set(0, ToString.toString(arguments.get(0)));
			return apply(arguments);
		}

		if (LuaType.getTypeOf(arguments.get(1)) == LuaType.NUMBER) {
			arguments.set(1, ToString.toString(arguments.get(1)));
			return apply(arguments);
		}

		NextMatch nextFunction = new NextMatch();
		Object s = arguments.get(0);
		Object p = arguments.get(1);

		return Arrays.asList(nextFunction, p, s);
	}
}
