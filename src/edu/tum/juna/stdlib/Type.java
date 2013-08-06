package edu.tum.juna.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Type extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { null };

	@Override
	public List<Object> apply(List<Object> arguments) {

		Preconditions.checkArguments("type", arguments, expectedTypes);

		Object argument = arguments.get(0);
		String type = LuaType.getTypeOf(argument).toString();
		LinkedList<Object> l = new LinkedList<Object>();
		l.add(type);
		return l;
	}

}
