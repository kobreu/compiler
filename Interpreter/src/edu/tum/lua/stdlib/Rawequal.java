package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;

public class Rawequal extends LuaFunctionNative{

	@Override
	public List<Object> apply(List<Object> arguments) {
		LinkedList<Object> result = new LinkedList<Object>();
		
		if (arguments.size() < 2) throw new LuaRuntimeException("bad argument");
		result.add(arguments.get(0).equals(arguments.get(1)));
		
		return result;
	}

}
