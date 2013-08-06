package edu.tum.juna.stdlib.io;

import static edu.tum.juna.Preconditions.checkArguments;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import edu.tum.juna.exceptions.LuaIOException;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;
import edu.tum.juna.types.LuaUserData;

public class FileClose extends LuaFunctionNative {
	private static final LuaType[][] types = { { LuaType.USERDATA } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);
		LuaUserData file = (LuaUserData) arguments.get(0);

		try {
			file.getRaf().close();
		} catch (IOException e) {
			throw new LuaIOException("attemp to use a closed file");
		}

		file.setRaf(null);

		return Collections.emptyList();
	}
}
