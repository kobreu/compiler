package edu.tum.lua.stdlib.string;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Format extends LuaFunctionNative {
	LuaType[][] expectedTypes = { { LuaType.STRING }, { LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.format", arguments, expectedTypes);

		List<Object> list = new LinkedList<>();
		for (int i = 1; i < arguments.size(); i++) {
			if (LuaType.getTypeOf(arguments.get(1)) != LuaType.STRING
					&& LuaType.getTypeOf(arguments.get(i)) != LuaType.NUMBER) {
				throw new LuaBadArgumentException(i, "string.format", "string or number", arguments.get(i).toString());
			}
			list.add(arguments.get(i));
		}

		String s = (String) arguments.get(0);
		Object[] o = list.toArray();

		for (int n = 0; n < o.length; n++) {
			int i = Find.getIndexOfPattern(s, "%[cdEefgGiouXxqs]")[0] + 1;
			char formChar = s.substring(i, i + 1).charAt(0);
			String replacement = "";
			switch (formChar) {
			case 'd':
			case 'i':
				if (LuaType.getTypeOf(o[n]) != LuaType.NUMBER) {
					throw new LuaBadArgumentException(n + 1, "string.format", LuaType.NUMBER.toString(),
							o[n].toString());
				}
				replacement = String.format("%d", ((Double) o[n]).intValue());
				break;
			case 'u':
				if (LuaType.getTypeOf(o[n]) != LuaType.NUMBER) {
					throw new LuaBadArgumentException(n + 1, "string.format", LuaType.NUMBER.toString(),
							o[n].toString());
				}
				long l = ((Double) o[n]).intValue() & 0xFFFFFFFFL;
				replacement = String.valueOf(l);
				break;

			case 'o':
			case 'x':
			case 'X':
				if (LuaType.getTypeOf(o[n]) != LuaType.NUMBER) {
					throw new LuaBadArgumentException(n + 1, "string.format", LuaType.NUMBER.toString(),
							o[n].toString());
				}
				replacement = String.format("%" + String.valueOf(formChar), ((Double) o[n]).intValue());
				break;

			case 'q':
				if (LuaType.getTypeOf(o[n]) != LuaType.STRING) {
					o[n] = ToString.toString(o[n]);
				}
				replacement = "\"" + ((String) o[n]) + "\"";
				break;

			case 's':
				replacement = String.format("%s", o[n]);
				break;

			case 'c':
				if (LuaType.getTypeOf(o[n]) != LuaType.NUMBER) {
					throw new LuaBadArgumentException(n + 1, "string.format", LuaType.NUMBER.toString(),
							o[n].toString());
				}
				replacement = String.valueOf((char) ((Double) o[n]).intValue());
				break;

			default:
				if (LuaType.getTypeOf(o[n]) != LuaType.NUMBER) {
					throw new LuaBadArgumentException(n + 1, "string.format", LuaType.NUMBER.toString(),
							o[n].toString());
				}

				replacement = String.format("%d", ((Double) o[n]).floatValue()).replace(',', '.');
			}

			s = s.replaceFirst("%[cdEefgGiouXxqs]", replacement);
		}

		return Collections.singletonList((Object) s);
	}
}
