package edu.tum.juna.stdlib.coroutine;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaThread;
import edu.tum.juna.types.LuaType;

public class Status extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.THREAD } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.status", arguments, expectedTypes);
		LuaThread t = (LuaThread) arguments.get(0);
		String status = null;
		if (t.getState() == Thread.State.RUNNABLE) {
			status = "running";
		} else if (t.isDead()) {
			status = "dead";
		} else if (t.isInterrupted() || t.getState() == Thread.State.NEW) {
			status = "suspended";
		} else {
			status = "normal";
		}
		return Arrays.asList((Object) status);
	}

}
