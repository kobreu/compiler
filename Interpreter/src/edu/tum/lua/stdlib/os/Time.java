package edu.tum.lua.stdlib.os;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Time extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { null, LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("os.time", arguments, expectedTypes);
		Calendar c = Calendar.getInstance();
		if (arguments.size() != 0) {
			LuaTable table = (LuaTable) arguments.get(0);
			if (table.get("year") == null)
				throw new LuaRuntimeException("field 'year' missing in date table");
			else
				c.set(Calendar.YEAR, (int) table.get("year"));
			if (table.get("month") == null)
				throw new LuaRuntimeException("field 'month' missing in date table");
			else
				c.set(Calendar.MONTH, (int) table.get("month"));
			if (table.get("day") == null)
				throw new LuaRuntimeException("field 'day' missing in date table");
			if (table.get("hour") != null)
				c.set(Calendar.HOUR, (int) table.get("hour"));
			if (table.get("min") != null)
				c.set(Calendar.MINUTE, (int) table.get("min"));
			if (table.get("sec") != null)
				c.set(Calendar.SECOND, (int) table.get("sec"));
		}
		java.util.Date d = c.getTime();
		double time = d.getTime();
		return Arrays.asList((Object) time);
	}

}
