package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.LuaRuntimeException;
<<<<<<< HEAD
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaFunctionNative;

public class Error extends LuaFunctionNative{
	
	@Override
	public List<Object> apply(List<Object> arguments){
		Object message = arguments.get(0);
		if (LuaType.getTypeOf(message) != LuaType.STRING) throw new IllegalArgumentException();
=======
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Error extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("error", arguments, expectedTypes);
		Object message = arguments.get(0);
>>>>>>> Parser
		throw new LuaRuntimeException(message.toString());
	}

}
