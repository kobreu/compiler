package edu.tum.lua.types;

import java.util.HashMap;
import java.util.Map;

public class LuaTable {

	private LuaFunction forwardFunction = null;
	private LuaTable forwardTable = null;
	private final Map<Object, Object> pairs;

	public LuaTable() {
		pairs = new HashMap<>();
	}

	public LuaTable(LuaTable table) {
		this();
		forwardTable = table;
	}

	public Object get(Object key) {
		Object value = pairs.get(key);

		if (value != null) {
			return value;
		}

		if (forwardTable != null) {
			return forwardTable.get(key);
		}

		if (forwardFunction != null) {
			return forwardFunction.apply(key).get(0);
		}

		return null;
	}

	public boolean getBoolean(Object key) {
		Object value = get(key);

		if (LuaType.getTypeOf(value) != LuaType.BOOLEAN) {
			throw new IllegalArgumentException();
		}

		return ((Boolean) value).booleanValue();
	}

	public LuaFunction getLuaFunction(Object key) {
		Object value = get(key);

		if (LuaType.getTypeOf(value) != LuaType.FUNCTION) {
			throw new IllegalArgumentException();
		}

		return (LuaFunction) value;
	}

	public LuaTable getLuaTable(Object key) {
		Object value = get(key);

		if (LuaType.getTypeOf(value) != LuaType.TABLE) {
			throw new IllegalArgumentException();
		}

		return (LuaTable) value;
	}

	public double getNumber(Object key) {
		Object value = get(key);

		if (LuaType.getTypeOf(value) != LuaType.NUMBER) {
			throw new IllegalArgumentException();
		}

		return ((Double) value).doubleValue();
	}

	public String getString(Object key) {
		Object value = get(key);

		if (LuaType.getTypeOf(value) != LuaType.STRING) {
			throw new IllegalArgumentException();
		}

		return (String) value;
	}

	public void set(Object key, Object value) {
		if (key == null || LuaType.getTypeOf(key) == null) {
			throw new IllegalArgumentException();
		}
		
		pairs.put(key, value);
	}

	public void setIndex(LuaFunction function) {
		forwardFunction = function;
		forwardTable = null;
	}

	public void setIndex(LuaTable table) {
		forwardFunction = null;
		forwardTable = table;
	}

	public void unset(Object key) {
		pairs.remove(key);
	}
}