package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

<<<<<<< HEAD
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Type extends LuaFunctionNative{

	@Override
	public List<Object> apply(List<Object> arguments) {
		
		if(arguments.size()!=1) throw new IllegalArgumentException();
		
=======
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Type extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { null };

	@Override
	public List<Object> apply(List<Object> arguments) {

		Preconditions.checkArguments("type", arguments, expectedTypes);

>>>>>>> Parser
		Object argument = arguments.get(0);
		String type = LuaType.getTypeOf(argument).toString();
		LinkedList<Object> l = new LinkedList<Object>();
		l.add(type);
		return l;
	}

}
