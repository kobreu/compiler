package edu.tum.juna.stdlib.io;

import static edu.tum.juna.Preconditions.checkArguments;

import java.util.Collections;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class FileFlush extends LuaFunctionNative {
	private static final LuaType[][] types = { { LuaType.USERDATA } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);

		// nothing to do here, solved automatically by RandomAccessFile

		return Collections.emptyList();
	}
}
