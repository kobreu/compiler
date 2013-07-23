package edu.tum.lua.stdlib;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;

public class Print extends LuaFunctionNative {
	
	private ToString s = new ToString();
	private PrintStream stdOut = System.out;
	
	@Override
	public List<Object> apply(List<Object> arguments){
		for (Object v : arguments){
			LinkedList<Object> arg = new LinkedList<Object>();
			arg.add(v);
			List<Object> l = s.apply(arg);
			stdOut.print(l.get(0).toString());
		}
		
		return null;
	}
	
	

}
