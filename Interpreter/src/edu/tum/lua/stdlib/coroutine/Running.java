package edu.tum.lua.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;

public class Running extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		Thread currentThread = Thread.currentThread();
		if (currentThread.getName().equals("main"))
			return null;
		return Arrays.asList((Object) currentThread);
	}

}
