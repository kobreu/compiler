package edu.tum.juna.stdlib.os;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;

public class Clock extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		double time = System.currentTimeMillis();
		return Arrays.asList((Object) time);
	}

}
