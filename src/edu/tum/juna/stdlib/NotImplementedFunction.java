package edu.tum.juna.stdlib;

import java.util.List;

import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.types.LuaFunctionNative;

public class NotImplementedFunction extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		throw new LuaRuntimeException("Not yet implemented");
	}
}
