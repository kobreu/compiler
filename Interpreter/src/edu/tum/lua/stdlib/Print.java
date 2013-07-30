package edu.tum.lua.stdlib;

import java.io.PrintStream;
<<<<<<< HEAD
import java.util.LinkedList;
=======
import java.util.Collections;
import java.util.Iterator;
>>>>>>> Parser
import java.util.List;

import edu.tum.lua.types.LuaFunctionNative;

public class Print extends LuaFunctionNative {
<<<<<<< HEAD
	
	private ToString s = new ToString();
	private PrintStream stdOut = System.out;
	
	@Override
	public List<Object> apply(List<Object> arguments){
		if (arguments.size()==0) stdOut.println();
		else{
			Object o = arguments.remove(0);
			LinkedList<Object> arg = new LinkedList<Object>();
			arg.add(o);
			List<Object> l = s.apply(arg);
			stdOut.print(l.get(0).toString());
		for (Object v : arguments){
			stdOut.print("      ");
			arg = new LinkedList<Object>();
			arg.add(v);
			l = s.apply(arg);
			stdOut.print(l.get(0).toString());
		}
		}
		return null;
	}
	
	
=======

	private final PrintStream stdOut = System.out;

	@Override
	public List<Object> apply(List<Object> arguments) {
		Iterator<Object> iter = arguments.iterator();

		while (iter.hasNext()) {
			stdOut.print(ToString.toString(iter.next()));

			if (iter.hasNext()) {
				stdOut.print("    ");
			}
		}

		stdOut.println();
		return Collections.emptyList();
	}
>>>>>>> Parser

}
