package edu.tum.lua.operator.relational;

import edu.tum.lua.types.LuaType;

public class EqOperator {
	
	public boolean apply(Object o1, Object o2) {
		
		// If LuaType is different return false
		if(LuaType.getTypeOf(o1) != LuaType.getTypeOf(o2)) {
			return false;
		}
		
		// Compare two numbers
		if(LuaType.getTypeOf(o1) == LuaType.NUMBER &&
			LuaType.getTypeOf(o2) == LuaType.NUMBER) {

			// TODO is comparison by reference or value?
			return o1 == o2;
		}
		
		// Compare two strings
		if(LuaType.getTypeOf(o1) == LuaType.STRING &&
			LuaType.getTypeOf(o2) == LuaType.STRING) {
			
			int comparison = ((String) o1).compareTo((String) o2);
			
			if (comparison == 0) {
				return true;
			} else {
				return false;
			}
			
		}
		
		// Compare tables using metamethod "eq"
		if(LuaType.getTypeOf(o1) == LuaType.TABLE &&
				LuaType.getTypeOf(o2) == LuaType.TABLE) {
			// TODO use metamethod "eq" to compare tables
			
		}
		
		// Everything else is compared by reference
		// tables without "eq", functions. (, and userdata, threads)
		return o1 == o2;
	}
	
	// TODO Handler name implementation?
	// Comparison of tables (and userdata) can be changed by setting
	// the eq metamethod.

}
