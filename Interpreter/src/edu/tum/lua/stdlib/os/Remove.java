package edu.tum.lua.stdlib.os;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Remove extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("os.remove", arguments, expectedTypes);
		try {
			Path p = Paths.get(arguments.get(0).toString());
			java.nio.file.Files.delete(p);
			return Collections.emptyList();
		} catch (NoSuchFileException e) {
			return Arrays.asList(null, (Object) "No such File or Directory");
		} catch (DirectoryNotEmptyException e) {
			return Arrays.asList(null, (Object) "Directory not empty");
		} catch (IOException e) {
			return Arrays.asList(null, (Object) e.toString());
		}
	}

}
