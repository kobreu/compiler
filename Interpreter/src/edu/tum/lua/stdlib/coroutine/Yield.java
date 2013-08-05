package edu.tum.lua.stdlib.coroutine;

import java.util.List;

import edu.tum.lua.exceptions.LuaInterruptException;
import edu.tum.lua.types.LuaFunctionNative;

public class Yield extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		throw new LuaInterruptException("");
	}

}
