package edu.tum.lua.stdlib.os;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Date extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { null, LuaType.STRING, LuaType.NUMBER },
			{ null, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("os.date", arguments, expectedTypes);
		if (arguments.size() == 0) {
			Calendar c = Calendar.getInstance();
			String time = c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR) + " "
					+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
			return Arrays.asList((Object) time);
		}
		String format = arguments.get(0).toString();
		Calendar c = Calendar.getInstance();
		if (arguments.size() > 1) {
			java.util.Date d = new java.util.Date((long) arguments.get(1));
			c.setTime(d);
		}
		if (format.charAt(0) == '!') {
			format = format.substring(1);
		}
		if (!format.equals("*t")) {
			return Arrays.asList((Object) format);
		}
		LuaTable date = new LuaTable();
		date.set("year", (double) c.get(Calendar.YEAR));
		date.set("month", (double) c.get(Calendar.MONTH));
		date.set("day", (double) c.get(Calendar.DAY_OF_MONTH));
		date.set("hour", (double) c.get(Calendar.HOUR));
		date.set("min", (double) c.get(Calendar.MINUTE));
		date.set("sec", (double) c.get(Calendar.SECOND));
		date.set("wday", (double) c.get(Calendar.DAY_OF_WEEK));
		date.set("yday", (double) c.get(Calendar.DAY_OF_YEAR));
		if (c.get(Calendar.AM_PM) == Calendar.AM) {
			date.set("isdst", true);
		} else {
			date.set("isdst", false);
		}
		return Arrays.asList((Object) date);
	}

}
