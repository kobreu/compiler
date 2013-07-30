package edu.tum.lua.stdlib.io;

import static edu.tum.lua.Preconditions.checkArguments;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaUserData;

public class FileWrite extends LuaFunctionNative {

	private LuaUserData file;
	private static final LuaType[][] types = { { LuaType.USERDATA } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("write", arguments, types);
		file = (LuaUserData) arguments.get(0);
		arguments = arguments.subList(1, arguments.size());

		int pos = -1;
		for (Object o : arguments) {
			pos++;
			if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
				arguments.set(pos, ToString.toString(o));
				continue;
			}
			if (LuaType.getTypeOf(o) != LuaType.STRING) {
				throw new LuaBadArgumentException(pos + 1, "file:write", "string or number", LuaType.getTypeOf(o)
						.toString());
			}
		}

		if (file.fm.equals("r")) {
			return Arrays.asList((Object) null, "bad file descriptor");
		}

		if (file.fm.equals("a+") || file.fm.equals("a")) {
			try {
				file.raf.seek(file.raf.length());
			} catch (IOException ioe) {
				return Arrays.asList((Object) null, "bad file descriptor");
			}
		}

		for (Object o : arguments) {
			try {
				file.raf.writeBytes((String) o);
			} catch (IOException ioe) {
				return Arrays.asList((Object) null, "error");
			}
		}

		return Collections.emptyList();
	}
}
