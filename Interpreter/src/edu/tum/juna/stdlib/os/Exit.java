package edu.tum.juna.stdlib.os;

import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Exit extends LuaFunctionNative {
	private static final LuaType[][] expectedTypes = { { null, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("os.exit", arguments, expectedTypes);
		if (arguments.size() == 0) {
			System.exit(0);
		} else {
			System.exit(((Double) arguments.get(0)).intValue());
		}

		return null;
	}

}
