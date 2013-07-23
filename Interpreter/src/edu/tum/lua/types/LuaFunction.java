package edu.tum.lua.types;

import java.util.List;

public interface LuaFunction {

	public List<Object> apply(List<Object> arguments);

	public List<Object> apply(Object... arguments);

}