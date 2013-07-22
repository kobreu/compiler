package edu.tum.lua;

import edu.tum.lua.types.LuaTable;

class GlobalEnvironment extends LuaTable {

	private final static GlobalEnvironment _G = new GlobalEnvironment();

	static LuaTable get() {
		return _G;
	}

	GlobalEnvironment() {
		set("_G", this);
	}
}
