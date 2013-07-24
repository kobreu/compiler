package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToNumber extends LuaFunctionNative {

	LuaType[][] expectedTypesOne = { { LuaType.STRING, LuaType.NUMBER } };
	LuaType[][] expectedTypesTwo = { { LuaType.STRING, LuaType.NUMBER }, { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		List<Object> list = new LinkedList<Object>();
		Object o = arguments.get(0);

		if (arguments.size() == 1) {
			Preconditions.checkArguments("tonumber", arguments, expectedTypesOne);

			switch (LuaType.getTypeOf(o)) {
			case NUMBER:
				list.add(o);
				break;
			case STRING:
				try {
					list.add(new Double(Double.parseDouble((String) o)));
				} catch (NumberFormatException e) {
					list.add(null);
				}
				break;
			default:
				list.add(null);
			}

			return list;
		} else {
			Preconditions.checkArguments("tonumber", arguments, expectedTypesTwo);

			Double b = (Double) arguments.get(1);

			if (b.intValue() > 36 || b.intValue() < 2) {
				throw new LuaRuntimeException("base expected as Number between 2 and 36");
			}

			if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
				return this.apply(Integer.toString(((Double) o).intValue()), b);
			}

			if (LuaType.getTypeOf(o) != LuaType.STRING) {

				list.add(null);
				return list;
			}

			try {
				System.out.println((String) o);
				System.out.println(b.intValue());
				Integer i = Integer.parseInt((String) o, b.intValue());
				list.add(new Double(i.intValue()));
			} catch (NumberFormatException e) {
				list.add(null);
			}
			return list;
		}
	}
}
