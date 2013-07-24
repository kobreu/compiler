package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Rawget extends LuaFunctionNative{

	@Override
	public List<Object> apply(List<Object> arguments) {
		LinkedList<Object> result = new LinkedList<Object>();
		
		if (arguments.size()<2) throw new LuaRuntimeException("bad argument");
		if (LuaType.getTypeOf(arguments.get(0)) != LuaType.TABLE) throw new LuaRuntimeException("bad argument");
		LuaTable table = (LuaTable) arguments.get(0);
		result.add(table.get(arguments.get(1)));
		
		return result;
	}

}
