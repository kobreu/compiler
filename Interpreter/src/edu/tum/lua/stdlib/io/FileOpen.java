package edu.tum.lua.stdlib.io;

import static edu.tum.lua.Preconditions.checkArguments;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaUserData;

public class FileOpen extends LuaFunctionNative {

	private static final LuaType[][] types = { { LuaType.STRING }, { null, LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);

		if (arguments.size() == 1) {
			return apply(arguments.get(0), "r");
		}

		File file = new File((String) arguments.get(0));
		String mode = (String) arguments.get(1);

		switch (mode) {
		case "r":
		case "r+":
			if (!file.isFile()) {
				return Arrays.asList((Object) null, "expected file does not existst");
			}
			break;
		case "w":
		case "w+":
			if (file.isFile()) {
				try {
					PrintWriter writer = new PrintWriter(file);
					writer.print("");
					writer.flush();
					writer.close();
				} catch (IOException e) {
					return Arrays.asList((Object) null, "expected file does not existst");
				}
			}
			break;
		case "a":
		case "a+":
			break;
		default:
			throw new LuaBadArgumentException(2, "io.open",
					"unknown access mode. expected mode with pattern \"[arw]%+?\"");
		}

		try {
			return Collections.singletonList((Object) new LuaUserData(file, mode));
		} catch (Exception e) {
			return Arrays.asList((Object) null, e.getMessage());
		}
	}
}