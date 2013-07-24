package edu.tum.lua;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import edu.tum.lua.types.LuaType;

public final class Preconditions {
	private static String printTypes(LuaType[] types) {
		StringBuilder builder = new StringBuilder();

		if (types == null)
			builder.append("any type");
		else {
			for (int i = 0; i < types.length; i++) {
				builder.append(types[i].toString());

				if (i < types.length - 1) {
					builder.append(" or ");
				}
			}
		}
		return builder.toString();
	}

	public static void checkArguments(String functionName, List<Object> arguments, LuaType[][] expectedTypes)
			throws LuaBadArgumentException {
		ListIterator<Object> argsIterator = arguments.listIterator();
		ListIterator<LuaType[]> typeIterator = Arrays.asList(expectedTypes).listIterator();

		while (typeIterator.hasNext()) {
			int pos = typeIterator.nextIndex() + 1;
			LuaType[] types = typeIterator.next();

			if (!argsIterator.hasNext()) {
				throw new LuaBadArgumentException(pos, functionName, printTypes(types), "no value");
			}

			if (types == null) {
				continue;
			}

			LuaType argsType = LuaType.getTypeOf(argsIterator.next());
			if (argsType == null)
				throw new RuntimeException();

			if (!Arrays.asList(types).contains(argsType)) {
				throw new LuaBadArgumentException(pos, functionName, printTypes(types), argsType.toString());
			}
		}
	}
}