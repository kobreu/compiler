package edu.tum.lua.stdlib.io;

import static edu.tum.lua.Preconditions.checkArguments;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaUserData;

public class FileClose extends LuaFunctionNative {
	private static final LuaType[][] types = { { LuaType.USERDATA } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);
		LuaUserData file = (LuaUserData) arguments.get(0);

		try {
			file.getRaf().close();
		} catch (IOException e) {
			throw new LuaRuntimeException("attemp to use a closed file");
		}

		file.setRaf(null);

		return Collections.emptyList();
	}
}
