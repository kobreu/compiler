package edu.tum.juna.stdlib.table;

import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.operator.list.LengthOperator;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class Insert extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("table.insert", arguments, expectedTypes);
		LuaTable table = (LuaTable) arguments.get(0);
		LengthOperator op = new LengthOperator();
		double pos = 1.0;
		double length = 0.0;
		try {
			length = (double) op.apply(table);
			pos = length + 1;
		} catch (LuaRuntimeException e) {
		}
		if (arguments.size() == 1) {
			throw new LuaRuntimeException("wrong number of arguments to table.insert");
		} else if (arguments.size() == 2) {
			table.set(pos, arguments.get(1));
		} else {
			if (LuaType.getTypeOf(arguments.get(1)) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(2, "table.insert", "number", LuaType.getTypeOf(arguments.get(1))
						.toString());
			}
			pos = (double) arguments.get(1);
			if (pos > length) {
				table.set(pos, arguments.get(2));
			} else {
				for (double i = length; i >= pos; i = i - 1) {
					table.set(i + 1, table.get(i));
				}
				table.set(pos, arguments.get(2));
			}
		}

		return null;
	}

}
