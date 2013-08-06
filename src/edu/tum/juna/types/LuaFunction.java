package edu.tum.juna.types;

import java.util.List;

public interface LuaFunction {

	public List<Object> apply(List<Object> arguments);

	public List<Object> apply(Object... arguments);

}