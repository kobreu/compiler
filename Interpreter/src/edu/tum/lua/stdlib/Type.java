package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Type extends LuaFunctionNative{

	@Override
	public List<Object> apply(List<Object> arguments) {
		
		if(arguments.size()!=1) throw new IllegalArgumentException();
		
		Object argument = arguments.get(0);
		String type = LuaType.getTypeOf(argument).toString();
		LinkedList<Object> l = new LinkedList<Object>();
		l.add(type);
		return l;
	}

}