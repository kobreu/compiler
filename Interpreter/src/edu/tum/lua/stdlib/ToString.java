package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToString extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		if (arguments.isEmpty()) {
			// TODO at least one argument necessary
			throw new IllegalStateException();
		}
		
		List<Object> list = new LinkedList<Object>();
		Object o = arguments.get(0);
		
		if (LuaType.getTypeOf(o) == LuaType.STRING) {
			list.add(o);
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.BOOLEAN) {
			list.add( Boolean.toString((boolean) o) );
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.NIL) {
			list.add("nil");
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			list.add(Double.toString((double) o));
			return list;
		}
		
		if (LuaType.getTypeOf(o) == LuaType.TABLE || LuaType.getTypeOf(o) == LuaType.FUNCTION) {
			list.add(o.toString());
			return list;
		}
		
		// TODO no known Type
		throw new IllegalStateException();
	}

}
