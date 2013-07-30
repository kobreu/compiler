package edu.tum.lua.stdlib.coroutine;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaThread;

public class Yield extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		LuaThread current = (LuaThread) Thread.currentThread();
		current.setReturnValue(arguments);
		current.interrupt();
		return Collections.emptyList();
	}

}
