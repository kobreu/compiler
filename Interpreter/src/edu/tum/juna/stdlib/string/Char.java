package edu.tum.juna.stdlib.string;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.exceptions.LuaIndexOutOfRangeException;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

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
				throw new LuaIndexOutOfRangeException("string.char");
			}
			s += String.valueOf((char) Math.rint(((Double) o).doubleValue()));
		}

		return Arrays.asList(new Object[] { s });
	}
}
