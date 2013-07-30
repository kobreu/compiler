package edu.tum.lua.stdlib.io;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaUserData;

public class FileSeek extends LuaFunctionNative {
	private static final LuaType[][] types = { { LuaType.USERDATA }, { null, LuaType.STRING }, { null, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);
		LuaUserData file = (LuaUserData) arguments.get(0);

		if (arguments.size() == 1) {
			return apply(file, "cur");
		}

		if (arguments.size() == 2) {
			return apply(file, arguments.get(1), 0.0);
		}

		long pos = 0;
		switch ((String) arguments.get(1)) {
		case "set":
			break;
		case "cur":
			try {
				pos = file.getRaf().getFilePointer();
			} catch (Exception e) {
				return Arrays.asList((Object) null, "error while handling file");
			}
			break;
		case "end":
			try {
				pos = file.getRaf().length();
				file.getRaf().seek(pos);
				return Arrays.asList((Object) new Double(pos));
			} catch (Exception e) {
				return Arrays.asList((Object) null, "error while handling file");
			}
		default:
			return Arrays.asList((Object) null, "unable to handle first argument");
		}

		pos += ((Double) arguments.get(2)).longValue();

		try {
			file.getRaf().seek(pos);
		} catch (Exception e) {
			return Arrays.asList((Object) null, "error while handling file");
		}

		return Arrays.asList((Object) new Double(pos));
	}
}
