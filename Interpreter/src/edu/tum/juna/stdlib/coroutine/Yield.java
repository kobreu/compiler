package edu.tum.juna.stdlib.coroutine;

import java.util.List;

import edu.tum.juna.exceptions.LuaInterruptException;
import edu.tum.juna.types.LuaFunctionNative;

public class Yield extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		throw new LuaInterruptException("");
	}

}
