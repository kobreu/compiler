package edu.tum.lua.stdlib.os;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Rename extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER },
			{ LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("os.rename", arguments, expectedTypes);
		try {
			Path p1 = Paths.get(arguments.get(0).toString());
			Path p2 = Paths.get(arguments.get(1).toString());
			java.nio.file.Files.move(p1, p2);
			return Collections.emptyList();
		} catch (NoSuchFileException e) {
			return Arrays.asList(null, (Object) "No such File or Directory");
		} catch (IOException e) {
			return Arrays.asList(null, (Object) e.toString());
		}
	}

}
