package edu.tum.lua.stdlib.string;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Match extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER }, { LuaType.STRING, LuaType.NUMBER },
			{ null, LuaType.NUMBER } };

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

		if (((String) arguments.get(1)).isEmpty()) {
			return Collections.singletonList(arguments.get(0));
		}

		Object[] index = new Find().apply(arguments).toArray();

		if (index[0] == null) {
			return Collections.singletonList((Object) null);
		}

		return Collections.singletonList((Object) ((String) arguments.get(0)).substring(
				((Double) index[0]).intValue() - 1, ((Double) index[1]).intValue()));
	}
}
