package edu.tum.lua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.tum.lua.ast.Exp;

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
		if (local.containsKey(key))
			return local.get(key);
		if (forward == null)
			return global.get(key);
		return forward.get(key);
	}

	public void setLocal(Object key, Object value) {
		local.put(key, value);
	}

	public void set(Object key, Object value) {
		if (local.containsKey(key))
			local.put(key, value);
		else if (forward == null)
			global.set(key, value);
		else
			forward.set(key, value);
	}

	public void setGlobalEnvironment(GlobalEnvironment env) {
		global = env;
	}

	public GlobalEnvironment getGlobalEnvironment() {
		return global;
	}

	public void assign(List<String> identifiers, List<Exp> expressions) {
		List<Object> values = new ArrayList<>(expressions.size());

		for (Exp exp : expressions) {
			Object value = LuaInterpreter.eval(exp, this);
			values.add(value);

			// TODO Adjust values!
		}

		for (int i = 0; i < identifiers.size(); i++) {
			set(identifiers.get(i), values.get(i));
		}
	}

}
