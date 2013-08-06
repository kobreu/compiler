package edu.tum.lua.stdlib.table;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.operator.list.LengthOperator;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Concat extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.TABLE } };

	@SuppressWarnings("unused")
	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("table.concat", arguments, expectedTypes);
		LuaTable table = (LuaTable) arguments.get(0);
		String result = "";
		LinkedList<Object> r = new LinkedList<Object>();

		String sep = "";
		if (arguments.size() > 1) {
			if (LuaType.getTypeOf(arguments.get(1)) != LuaType.STRING
					&& LuaType.getTypeOf(arguments.get(1)) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(2, "table.concat", "string", LuaType.getTypeOf(arguments.get(1))
						.toString());
			}
			sep = arguments.get(1).toString();
		}

		double begin = 1.0;
		if (arguments.size() > 2) {
			if (LuaType.getTypeOf(arguments.get(2)) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(3, "table.concat", "number", LuaType.getTypeOf(arguments.get(2))
						.toString());
			}
			begin = (double) arguments.get(2);
		}

		LengthOperator l = new LengthOperator();
		double end = (double) l.apply(table);

		if (arguments.size() > 3) {
			if (LuaType.getTypeOf(arguments.get(3)) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(4, "table.concat", "number", LuaType.getTypeOf(arguments.get(3))
						.toString());
			}
			end = (double) arguments.get(3);
		}

		if (begin > end) {
			r.add(result);
			return r;
		}
		if (table.get(begin) == null) {
			throw new LuaRuntimeException("invalid value at index " + begin + " in table for concat");
		}
		result = table.get(begin).toString();
		for (double i = begin + 1; i <= end; i = i + 1) {
			if (LuaType.getTypeOf(table.get(i)) != LuaType.NUMBER && LuaType.getTypeOf(table.get(i)) != LuaType.STRING) {
				throw new LuaRuntimeException("invalid value at index " + i + " in table for concat");
			}
			result = result.concat(sep);
			result = result.concat(table.get(i).toString());
		}
		r.add(result);
		return r;
	}

}
