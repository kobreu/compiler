package edu.tum.lua.types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.tum.lua.exceptions.LuaRuntimeException;

public class LuaTable implements Iterable<Map.Entry<Object, Object>> {

	private LuaTable metatable;
	private final Map<Object, Object> pairs;

	public LuaTable() {
		pairs = new HashMap<>();
		metatable = null;
	}

	public Object get(Object key) {
		Object value = rawget(key);

		if (value != null) {
			return value;
		}

		Object h = metatable != null ? metatable.get("__index") : null;

		if (h == null) {
			return null;
		}

		/* call handler */
		if (LuaType.getTypeOf(h) == LuaType.FUNCTION) {
			return ((LuaFunction) h).apply(this, key).get(0);
		}

		LuaTable t = (LuaTable) h;
		return t.get(key);
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

	public LuaTable getMetatable() {
		if (metatable != null && metatable.get("__metatable") != null) {
			return (LuaTable) metatable.get("__metatable");
		}

		return metatable;
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

	public boolean isEmpty() {
		return pairs.isEmpty();
	}

	@Override
	public Iterator<Entry<Object, Object>> iterator() {
		return pairs.entrySet().iterator();
	}

	public Set<Object> keySet() {
		return pairs.keySet();
	}

	public Object rawget(Object key) {
		return pairs.get(key);
	}

	public void rawset(Object key, Object value) {
		if (key == null) {
			throw new LuaRuntimeException("table index is nil");
		}

		if (value == null) {
			pairs.remove(key);
			return;
		}

		pairs.put(key, value);
	}

	public void set(Object key, Object value) {
		Object h;

		/* key is already present in the table */
		if (rawget(key) != null) {
			rawset(key, value);
			return;
		}

		h = metatable != null ? metatable.get("__newindex") : null;

		/* there is no __newindex event handler */
		if (h == null) {
			rawset(key, value);
			return;
		}

		/* call handler */
		if (LuaType.getTypeOf(h) == LuaType.FUNCTION) {
			((LuaFunction) h).apply(this, key, value);
			return;
		}

		LuaTable t = (LuaTable) h;
		t.set(key, value);
	}

	public void setMetaIndex(LuaFunction function) {
		if (metatable == null) {
			throw new IllegalStateException();
		}

		metatable.set("__index", function);
	}

	public void setMetaIndex(LuaTable table) {

		if (metatable == null) {
			throw new IllegalStateException();
		}

		metatable.set("__index", table);
	}

	public void setMetatable(LuaTable metatable) {
		if (metatable != null && metatable.get("__metatable") != null) {
			throw new LuaRuntimeException("cannot change a protected metatable");
		}

		this.metatable = metatable;
	}

}