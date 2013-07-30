package edu.tum.lua.stdlib.table;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.operator.list.LengthOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Sort extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.TABLE } };

	private static class Comp implements Comparator<Object> {

		private LuaFunction comp;
		private final int type;

		public Comp() {
			type = 1;
		}

		public Comp(LuaFunction f) {
			type = 2;
			comp = f;
		}

		@Override
		public int compare(Object o1, Object o2) {
			if (type == 1) {
				String s1 = o1.toString();
				String s2 = o2.toString();
				return s1.compareTo(s2);
			} else {
				boolean b1 = (boolean) comp.apply(o1, o2).get(0);
				boolean b2 = (boolean) comp.apply(o2, o1).get(0);
				if (!b1)
					return 1;
				else if (!b2)
					return -1;
				else
					return 0;
			}
		}

	}

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("table.sort", arguments, expectedTypes);
		Comp comp = null;
		LuaTable table = (LuaTable) arguments.get(0);

		if (arguments.size() > 1) {
			if (LuaType.getTypeOf(arguments.get(1)) != LuaType.FUNCTION)
				throw new LuaBadArgumentException(2, "table.sort", "function", LuaType.getTypeOf(arguments.get(1))
						.toString());
			comp = new Comp((LuaFunction) arguments.get(1));
		}

		double length = 0.0;
		LengthOperator op = new LengthOperator();
		try {
			length = (double) op.apply(table);
		} catch (LuaRuntimeException e) {
		}

		if (length != 0.0) {
			LuaType type = LuaType.getTypeOf(table.get(1.0));
			if (type == LuaType.STRING && comp == null)
				comp = new Comp();
			Object[] t = new Object[(int) length];
			for (double pos = 1.0; pos <= length; pos = pos + 1) {
				if (LuaType.getTypeOf(table.get(pos)) != type)
					throw new LuaRuntimeException("attempt to compare " + type.toString() + " with "
							+ LuaType.getTypeOf(table.get(pos)).toString());
				t[(int) pos - 1] = table.get(pos);
			}
			if (comp == null && type != LuaType.NUMBER)
				throw new LuaRuntimeException("attempt to compare two " + type.toString() + " values");
			else if (comp == null)
				Arrays.sort(t);
			else
				Arrays.sort(t, comp);
			for (int i = 0; i < t.length; i++) {
				table.set((double) (i + 1), t[i]);
			}
		}
		return Collections.emptyList();
	}

}
