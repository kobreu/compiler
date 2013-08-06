package edu.tum.juna.stdlib.coroutine;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.exceptions.LuaInterruptException;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaThread;
import edu.tum.juna.types.LuaType;

public class Resume extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.THREAD } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("coroutine.resume", arguments, expectedTypes);
		LuaThread t = (LuaThread) arguments.get(0);
		t.setArguments(arguments.subList(1, arguments.size()));
		try {
			t.run();
			while (true) {
				if (!t.isAlive()) {
					LinkedList<Object> l = new LinkedList<Object>();
					l.addFirst(true);
					if (t.getReturnValue() != null) {
						Iterator<Object> iter = t.getReturnValue().iterator();
						while (iter.hasNext()) {
							l.addLast(iter.next());
						}
					}
					return l;
				}
			}
		} catch (LuaInterruptException e) {
			LinkedList<Object> l = new LinkedList<Object>();
			l.addFirst(true);
			if (t.getReturnValue() != null) {
				Iterator<Object> iter = t.getReturnValue().iterator();
				while (iter.hasNext()) {
					l.addLast(iter.next());
				}
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return Arrays.asList((Object) false, e.getMessage());
		}
	}
}
