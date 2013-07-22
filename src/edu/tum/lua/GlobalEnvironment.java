package edu.tum.lua;

import edu.tum.lua.types.LuaTable;

public class GlobalEnvironment extends LuaTable {

	private final static GlobalEnvironment _G = new GlobalEnvironment();

	public static LuaTable get() {
		return _G;
	}

	private GlobalEnvironment() {
		set("_G", this);
	}
}
