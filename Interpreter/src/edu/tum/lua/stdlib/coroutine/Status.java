package edu.tum.lua.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaThread;
import edu.tum.lua.types.LuaType;

public class Status extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.THREAD } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.status", arguments, expectedTypes);
		LuaThread t = (LuaThread) arguments.get(0);
		String status = null;
		if (t.getState() == Thread.State.RUNNABLE)
			status = "running";
		else if (t.isDead())
			status = "dead";
		else if (t.isInterrupted() || t.getState() == Thread.State.NEW)
			status = "suspended";
		else
			status = "normal";
		return Arrays.asList((Object) status);
	}

}
