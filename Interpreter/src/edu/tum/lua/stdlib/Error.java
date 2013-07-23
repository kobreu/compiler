package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaFunctionNative;

public class Error extends LuaFunctionNative{
	
	@Override
	public List<Object> apply(List<Object> arguments){
		Object message = arguments.get(0);
		if (LuaType.getTypeOf(message) != LuaType.STRING) throw new IllegalArgumentException();
		throw new LuaRuntimeException(message.toString());
	}

}
