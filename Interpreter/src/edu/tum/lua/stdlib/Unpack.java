package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.operator.list.LengthOperator;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Unpack extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("unpack", arguments, expectedTypes);
		LuaTable table = (LuaTable) arguments.get(0);

		double from = 1;
		if (arguments.size() > 1) {
			if (LuaType.getTypeOf(arguments.get(1)) != LuaType.NUMBER)
				throw new LuaBadArgumentException(2, "unpack", "number", LuaType.getTypeOf(arguments.get(1)).toString());
			from = (double) arguments.get(1);
		}

		LengthOperator op = new LengthOperator();
		double length = 0;
		try {
			length = (double) op.apply(table);
		} catch (NoSuchMethodException e) {
		}

		double to = length;
		if (arguments.size() > 2) {
			if (LuaType.getTypeOf(arguments.get(2)) != LuaType.NUMBER)
				throw new LuaBadArgumentException(3, "unpack", "number", LuaType.getTypeOf(arguments.get(1)).toString());
			to = (double) arguments.get(2);
		}

		LinkedList<Object> result = new LinkedList<Object>();
		for (double i = from; i < Math.max(0, from); i = i + 1) {
			result.add(null);
		}
		for (double i = Math.max(0, from); i <= Math.min(length, to); i = i + 1) {
			result.add(table.get(i));
		}
		for (double i = Math.min(length, to) + 1; i <= to; i = i + 1) {
			result.add(null);
		}
		return result;
	}

}
