package edu.tum.lua.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaThread;
import edu.tum.lua.types.LuaType;

public class Resume extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.THREAD } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.resume", arguments, expectedTypes);
		LuaThread t = (LuaThread) arguments.get(0);
		t.setArguments(arguments.subList(1, arguments.size()));
		try {
			t.run();
			while (true) {
				if (t.isInterrupted()) {
					return Arrays.asList(true, t.getReturnValue());
				} else if (!t.isAlive()) {
					return Arrays.asList(true, t.getReturnValue());
				}
				t.wait(5);
			}
		} catch (Exception e) {
			return Arrays.asList((Object) false, e.getMessage());
		}
	}

}
