package edu.tum.juna.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class RawEqual extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { null, null };

	@Override
	public List<Object> apply(List<Object> arguments) {
		LinkedList<Object> result = new LinkedList<Object>();
		Preconditions.checkArguments("rawequal", arguments, expectedTypes);
		result.add(arguments.get(0).equals(arguments.get(1)));

		return result;
	}

}
