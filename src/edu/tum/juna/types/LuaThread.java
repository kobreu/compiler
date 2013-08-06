package edu.tum.juna.types;

import java.util.Collections;
import java.util.List;

public class LuaThread extends Thread {

	private final LuaFunction body;
	private List<Object> arguments;
	private List<Object> returnValue;
	private boolean isDead;

	public LuaThread(LuaFunction f) {
		body = f;
		arguments = Collections.emptyList();
		isDead = false;
	}

	@Override
	public void run() {
		returnValue = body.apply(arguments);
		isDead = true;
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

	public boolean isDead() {
		return isDead;
	}

}
