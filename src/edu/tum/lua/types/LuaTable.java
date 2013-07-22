package edu.tum.lua.types;

import java.util.HashMap;
import java.util.Map;

public class LuaTable {

	private final Map<Object, LuaType> types;
	private final Map<Object, Object> pairs;

	public LuaTable() {
		types = new HashMap<>();
		pairs = new HashMap<>();
	}

	public LuaType getType(String key) {
		LuaType type = types.get(key);

		if (type == null) {
			return LuaType.NIL;
		}

		return type;
	}

	public Object get(Object key) {
		return pairs.get(key);
	}

	public LuaTable getLuaTable(Object key) {
		if (types.get(key) != LuaType.TABLE) {
			throw new IllegalArgumentException();
		}

		return (LuaTable) pairs.get(key);
	}

	public String getString(Object key) {
		if (types.get(key) != LuaType.STRING) {
			throw new IllegalArgumentException();
		}

		return (String) pairs.get(key);
	}

	public double getNumber(Object key) {
		if (types.get(key) != LuaType.NUMBER) {
			throw new IllegalArgumentException();
		}

		return ((Double) pairs.get(key)).doubleValue();
	}

	public LuaFunction getLuaFunction(Object key) {
		if (types.get(key) != LuaType.FUNCTION) {
			throw new IllegalArgumentException();
		}

		return (LuaFunction) pairs.get(key);
	}

	public boolean getBoolean(Object key) {
		if (types.get(key) != LuaType.BOOLEAN) {
			throw new IllegalArgumentException();
		}

		return ((Boolean) pairs.get(key)).booleanValue();
	}

	public void set(Object key, LuaTable value) {
		types.put(key, LuaType.TABLE);
		pairs.put(key, value);
	}

	public void set(Object key, String value) {
		types.put(key, LuaType.STRING);
		pairs.put(key, value);
	}

	public void set(Object key, double value) {
		types.put(key, LuaType.NUMBER);
		pairs.put(key, value);
	}

	public void set(Object key, LuaFunction value) {
		types.put(key, LuaType.FUNCTION);
		pairs.put(key, value);
	}

	public void set(Object key, boolean value) {
		types.put(key, LuaType.BOOLEAN);
		pairs.put(key, value);
	}

	public void unset(Object key) {
		types.remove(key);
		pairs.remove(key);
	}

}
