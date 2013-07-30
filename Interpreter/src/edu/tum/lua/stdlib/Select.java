package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaIndexOutOfRangeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Select extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.NUMBER, LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("select", arguments, expectedTypes);

		Object firstArgument = arguments.get(0);
		if (LuaType.getTypeOf(firstArgument) == LuaType.NUMBER) {
			double index = (double) firstArgument;
			if (index < 1)
				throw new LuaIndexOutOfRangeException(1, "select");
			for (int i = 0; i < index; i++) {
				arguments.remove(0);
			}
			return arguments;
		} else if (LuaType.getTypeOf(firstArgument) == LuaType.STRING) {
			if (firstArgument.toString().equals("#")) {
				if (arguments.size() == 1) {
					LinkedList<Object> l = new LinkedList<Object>();
					l.add(0.0);
					return l;
				} else {
					arguments.remove(0);
					return arguments;
				}
			}
		}
		throw new LuaBadArgumentException(1, "select", "number", LuaType.getTypeOf(firstArgument).toString());
	}

}
