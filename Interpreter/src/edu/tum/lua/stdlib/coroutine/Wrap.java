package edu.tum.lua.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaThread;
import edu.tum.lua.types.LuaType;

public class Wrap extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.FUNCTION } };

	private class CustomedResume extends LuaFunctionNative {
		LuaThread thread;

		public CustomedResume(LuaFunction arg) {
			thread = new LuaThread(arg);
		}

		@Override
		public List<Object> apply(List<Object> arguments) {
			Resume r = new Resume();
			return r.apply(thread, arguments);
		}
	}

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.wrap", arguments, expectedTypes);
		CustomedResume r = new CustomedResume((LuaFunction) arguments.get(0));
		return Arrays.asList((Object) r);
	}

}
