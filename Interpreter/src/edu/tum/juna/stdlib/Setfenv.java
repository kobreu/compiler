package edu.tum.juna.stdlib;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.Preconditions;
import edu.tum.juna.exceptions.LuaNotSupportedException;
import edu.tum.juna.types.LuaFunctionInterpreted;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Setfenv extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.FUNCTION, LuaType.NUMBER }, { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("setfenv", arguments, expectedTypes);
		Object f = arguments.get(0);
		if (LuaType.getTypeOf(f) == LuaType.NUMBER) {
			throw new LuaNotSupportedException();
		}
		if (f instanceof LuaFunctionNative) {
			throw new LuaNotSupportedException();
		}
		((LuaFunctionInterpreted) f).setGlobalEnvironment((GlobalEnvironment) arguments.get(1));
		return Arrays.asList(f);
	}

}
