package edu.tum.juna.stdlib;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.exceptions.LuaNotSupportedException;
import edu.tum.juna.types.LuaFunctionInterpreted;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Getfenv extends LuaFunctionNative {

	private final GlobalEnvironment globalEnvironment;

	public Getfenv(GlobalEnvironment globalEnvironment) {
		this.globalEnvironment = globalEnvironment;
	}

	@Override
	public List<Object> apply(List<Object> arguments) {
		if (arguments.isEmpty()) {
			return Arrays.asList((Object) globalEnvironment);
		}
		Object f = arguments.get(0);
		if (LuaType.getTypeOf(f) == LuaType.NUMBER) {
			throw new LuaNotSupportedException();
		}
		if (LuaType.getTypeOf(f) != LuaType.FUNCTION) {
			throw new LuaBadArgumentException(1, "getfenv", "number", LuaType.getTypeOf(f).toString());
		}
		if (f instanceof LuaFunctionNative) {
			return Arrays.asList((Object) globalEnvironment);
		}
		return Arrays.asList((Object) ((LuaFunctionInterpreted) f).getGlobalEnvironment());
	}

}
