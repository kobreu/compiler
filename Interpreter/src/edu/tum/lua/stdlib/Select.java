package edu.tum.lua.stdlib;

import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Select extends LuaFunctionNative{

	@Override
	public List<Object> apply(List<Object> arguments) {
		if (arguments.size()<1) throw new IllegalArgumentException();
		Object firstArgument = arguments.get(0);
		if (LuaType.getTypeOf(firstArgument)==LuaType.NUMBER){
			double index = (double) firstArgument;
			if (arguments.size()<index) throw new IllegalArgumentException();
			for (int i = 0; i< index;i++){
				arguments.remove(0);
			}
			return arguments;
		}
		else if (LuaType.getTypeOf(firstArgument) == LuaType.STRING){
			if (!firstArgument.toString().equals("#")) throw new IllegalArgumentException();
			if (arguments.size()==1) throw new IllegalArgumentException();
			arguments.remove(0);
			return arguments;
		}
		throw new IllegalArgumentException();
	}

}
