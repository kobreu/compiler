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

	public static LuaType getTypeOf(Object object) {
		if (object instanceof LuaFunction) {
			return FUNCTION;
		}

		if (object instanceof String) {
			return STRING;
		}

		return null;
	}
}
