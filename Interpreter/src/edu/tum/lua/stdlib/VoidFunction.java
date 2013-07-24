package edu.tum.lua.stdlib;

import java.util.Collections;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;

public class VoidFunction extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		return Collections.emptyList();
	}

}
