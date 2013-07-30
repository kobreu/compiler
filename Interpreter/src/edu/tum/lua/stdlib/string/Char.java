package edu.tum.lua.stdlib.string;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Char extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		int i = 1;
		for (Object o : arguments) {
			i++;
			if (LuaType.getTypeOf(o) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(i, "string.char", LuaType.NUMBER.toString(), o.toString());
			}
		}

		String s = "";

		i = 1;
		for (Object o : arguments) {
			i++;
			if (((Double) o).intValue() < 0 || ((Double) o).intValue() > 255) {
				throw new LuaRuntimeException("value out of range");
			}
			s += String.valueOf((char) Math.rint(((Double) o).doubleValue()));
		}

		return Arrays.asList(new Object[] { s });
	}
}
