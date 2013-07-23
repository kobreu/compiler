package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;

public class Error extends LuaFunctionNative{
	
	@Override
	public List<Object> apply(List<Object> arguments){
		Object message = arguments.get(0);
		throw new RuntimeException(message.toString());
	}

}
