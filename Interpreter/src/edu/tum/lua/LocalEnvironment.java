package edu.tum.lua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.tum.lua.ast.Exp;

public class LocalEnvironment {

	private final Map<Object, Object> local;
	private final LocalEnvironment forward;
	private Environment global;

	public LocalEnvironment() {
		local = new HashMap<Object, Object>();
		global = Environment.getGlobalEnvironment();
		forward = null;
	}

	public LocalEnvironment(LocalEnvironment l) {
		local = new HashMap<>();
		global = Environment.getGlobalEnvironment();
		forward = l;
	}

	public Object get(Object key) {
		if (local.containsKey(key))
			return local.get(key);
		if (forward == null)
			return global.get(key);
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
		if (local.containsKey(key))
			local.put(key, value);
		else if (forward == null)
			global.set(key, value);
		else
			forward.set(key, value);
	}

	public void setGlobalEnvironment(Environment env) {
		global = env;
	}

	public Environment getGlobalEnvironment() {
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
