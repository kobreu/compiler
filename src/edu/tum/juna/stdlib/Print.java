package edu.tum.juna.stdlib;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.tum.juna.types.LuaFunctionNative;

public class Print extends LuaFunctionNative {

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

}
