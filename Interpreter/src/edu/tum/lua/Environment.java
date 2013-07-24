package edu.tum.lua;

import java.util.ArrayList;
import java.util.List;

import edu.tum.lua.ast.Exp;
import edu.tum.lua.stdlib.Assert;
import edu.tum.lua.stdlib.Error;
import edu.tum.lua.stdlib.GetMetatable;
import edu.tum.lua.stdlib.Next;
import edu.tum.lua.stdlib.NotImplementedFunction;
import edu.tum.lua.stdlib.Print;
import edu.tum.lua.stdlib.RawEqual;
import edu.tum.lua.stdlib.RawGet;
import edu.tum.lua.stdlib.RawSet;
import edu.tum.lua.stdlib.Select;
import edu.tum.lua.stdlib.SetMetatable;
import edu.tum.lua.stdlib.ToNumber;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.stdlib.Type;
import edu.tum.lua.stdlib.Unpack;
import edu.tum.lua.types.LuaTable;

public class Environment extends LuaTable {

	private final static Environment _G = new Environment();

	public static Environment getGlobalEnvironment() {
		return _G;
	}

	/**
	 * Create a new global environment.
	 */
	Environment() {
		set("assert", new Assert());
		set("collectgarbage", new NotImplementedFunction());
		set("dofile", new NotImplementedFunction());
		set("error", new Error());
		set("_G", this);
		set("getfenv", new NotImplementedFunction());
		set("getmetatable", new GetMetatable());
		set("ipairs", new NotImplementedFunction());
		set("load", new NotImplementedFunction());
		set("loadfile", new NotImplementedFunction());
		set("loadstring", new NotImplementedFunction());
		set("next", new Next());
		set("pairs", new NotImplementedFunction());
		set("pcall", new NotImplementedFunction());
		set("print", new Print());
		set("rawequal", new RawEqual());
		set("rawget", new RawGet());
		set("rawset", new RawSet());
		set("select", new Select());
		set("setfenv", new NotImplementedFunction());
		set("setmetatable", new SetMetatable());
		set("tonumber", new ToNumber());
		set("tostring", new ToString());
		set("type", new Type());
		set("unpack", new Unpack());
		set("_VERSION", "Java Lua -1");
		set("xpcall", new NotImplementedFunction());
	}

	public Environment(Environment forward) {
		super(forward);
	}

	public void assign(List<String> identifiers, List<Exp> expressions) {
		List<Object> values = new ArrayList<>(expressions.size());

		for (Exp exp : expressions) {
			values.add(LuaInterpreter.eval(exp, this));
		}

		for (int i = 0; i < identifiers.size(); i++) {
			set(identifiers.get(i), values.get(i));
		}
	}
}
