package edu.tum.lua.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LuaTable {

	private final Map<Object, Object> pairs;
	private LuaTable metatable;

	public LuaTable() {
		pairs = new HashMap<>();
		metatable = null;
	}

	public LuaTable(LuaTable table) {
		this();
		setIndex(table);
	}

	public Object get(Object key) {
		if (LuaType.getTypeOf(key) == LuaType.NIL) {
			throw new IllegalArgumentException();
		}

		Object value = pairs.get(key);

		if (value != null) {
			return value;
		}

		if (metatable != null) {
			Object __index = metatable.get("__index");

			if (LuaType.getTypeOf(__index) == LuaType.TABLE) {
				return ((LuaTable) __index).get(key);
			} else if (LuaType.getTypeOf(__index) == LuaType.FUNCTION) {
				return ((LuaFunction) __index).apply(key).get(0);
			} else if (LuaType.getTypeOf(__index) == LuaType.NIL) {
				return null;
			} else {
				throw new RuntimeException("Not supported yet: " + LuaType.getTypeOf(__index));
			}
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

	public Set<Entry<Object, Object>> getIterator() {
		Set<Entry<Object, Object>> iter = pairs.entrySet();
		return iter;
	}

	public boolean isEmpty() {
		return pairs.isEmpty();
	}

	public void set(Object key, Object value) {
		if (key == null || LuaType.getTypeOf(key) == null) {
			throw new IllegalArgumentException();
		}

		pairs.put(key, value);
	}

	public void setIndex(LuaFunction function) {
		if (metatable == null) {
			throw new IllegalStateException();
		}

		metatable.set("__index", function);
	}

	public void setIndex(LuaTable table) {
		if (metatable == null) {
			throw new IllegalStateException();
		}

		metatable.set("__index", table);
	}

	public void unset(Object key) {
		pairs.remove(key);
	}

	public LuaTable getMetatable() {
		return metatable;
	}

	public void setMetatable(LuaTable metatable) {
		this.metatable = metatable;
	}
}