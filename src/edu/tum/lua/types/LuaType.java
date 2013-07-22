package edu.tum.lua.types;

public enum LuaType {
	TABLE("table"), STRING("string"), NUMBER("number"), FUNCTION("function"), BOOLEAN("boolean"), NIL("nil");
	
	String id;
	
	LuaType(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id;
	}
}
