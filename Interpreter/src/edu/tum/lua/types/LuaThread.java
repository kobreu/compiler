package edu.tum.lua.types;

import java.util.Collections;
import java.util.List;

public class LuaThread extends Thread {

	private final LuaFunction body;
	private List<Object> arguments;
	private List<Object> returnValue;

	public LuaThread(LuaFunction f) {
		body = f;
		arguments = Collections.emptyList();
	}

	@Override
	public void run() {
		returnValue = body.apply(arguments);
	}

	public void setArguments(List<Object> arg) {
		arguments = arg;
	}

	public List<Object> getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(List<Object> value) {
		returnValue = value;
	}

}
