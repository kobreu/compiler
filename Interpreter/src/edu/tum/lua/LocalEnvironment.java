package edu.tum.lua;

import java.util.HashMap;
import java.util.Map;

public class LocalEnvironment {

	private final Map<Object, Object> local;
	private final LocalEnvironment forward;
	private GlobalEnvironment global;

	public LocalEnvironment() {
		this(new GlobalEnvironment(), null);
	}

	public LocalEnvironment(LocalEnvironment l) {
		this(l.global, l);
	}

	public LocalEnvironment(GlobalEnvironment g) {
		this(g, null);
	}

	public LocalEnvironment(GlobalEnvironment g, LocalEnvironment l) {
		local = new HashMap<>();
		global = g;
		forward = l;
	}

	public Object get(Object key) {
		if (local.containsKey(key)) {
			return local.get(key);
		}
		if (forward == null) {
			return global.get(key);
		}
		return forward.get(key);
	}

	/**
	 * put a variable directly into this local environment
	 */
	public void setLocal(Object key, Object value) {
		local.put(key, value);
	}

	/**
	 * change the value of the variable if she already exist in the different
	 * local environments, otherwise put it in the global environment
	 */
	public void set(Object key, Object value) {
		if (local.containsKey(key)) {
			local.put(key, value);
		} else if (forward == null) {
			global.set(key, value);
		} else {
			forward.set(key, value);
		}
	}

	public void setGlobalEnvironment(GlobalEnvironment env) {
		global = env;
	}

	public GlobalEnvironment getGlobalEnvironment() {
		return global;
	}

}
