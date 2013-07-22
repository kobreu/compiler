package edu.tum.lua;

import java.util.List;

import edu.tum.lua.ast.Chunk;
import edu.tum.lua.ast.Stat;
import edu.tum.lua.types.LuaTable;

public class LuaInterpreter {

	public static List<Object> eval(Chunk chunk) {
		return eval(chunk, new GlobalEnvironment());
	}
	
	public static List<Object> eval(Chunk chunk, LuaTable environment) {
		for (Stat statement : chunk.getStats()) {
			switch(statement.getType()) {
			case BAR:
				break;
			
			case RETURN:
				return null;
				
			case ASSIGN:
				environment.get("foo");
				environment.set("foo", "bar");
				break;
				
			case FOO:
				break;
				
			default:
				break;
			}
		}
		
		return null;
	}
	
}
