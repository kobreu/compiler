package edu.tum.juna.stdlib.io;

import static edu.tum.juna.Preconditions.checkArguments;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;
import edu.tum.juna.types.LuaUserData;

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

		if (file.getFm().equals("r")) {
			return Arrays.asList((Object) null, "bad file descriptor");
		}

		if (file.getFm().equals("a+") || file.getFm().equals("a")) {
			try {
				file.getRaf().seek(file.getRaf().length());
			} catch (IOException ioe) {
				return Arrays.asList((Object) null, "bad file descriptor");
			}
		}

		for (Object o : arguments) {
			try {
				file.getRaf().writeBytes((String) o);
			} catch (IOException ioe) {
				return Arrays.asList((Object) null, "error");
			}
		}

		return Collections.emptyList();
	}
}
