package edu.tum.lua.types;

public enum LuaType {
	BOOLEAN("boolean"), FUNCTION("function"), NIL("nil"), NUMBER("number"), STRING("string"), TABLE("table");

	public static LuaType getTypeOf(Object object) {
		if (object == null) {
			return NIL;
		}

		if (object instanceof Boolean) {
			return BOOLEAN;
		}

		if (object instanceof LuaFunction) {
			return FUNCTION;
		}

		if (object instanceof Double) {
			return NUMBER;
		}

		if (object instanceof String) {
			return STRING;
		}

		if (object instanceof LuaTable) {
			return TABLE;
		}

		throw new IllegalArgumentException("Expected Lua Type, got " + object.getClass().toString());
	}

	String id;

	LuaType(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}
}
