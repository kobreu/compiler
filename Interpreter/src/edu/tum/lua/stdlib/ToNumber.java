package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
<<<<<<< HEAD
=======
import edu.tum.lua.Preconditions;
>>>>>>> Parser
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToNumber extends LuaFunctionNative {

<<<<<<< HEAD
	@Override
	public List<Object> apply(List<Object> arguments) {		
		if (arguments.isEmpty()) {
			throw new LuaRuntimeException("no argument");
		}
		
		List<Object> list = new LinkedList<Object>();
		Object o = arguments.get(0);
		
		if (arguments.size() == 1) {
			
			if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
				list.add(o);
				return list;
			}
			
			if (LuaType.getTypeOf(o) == LuaType.STRING) {
				try {
					list.add(new Double(Double.parseDouble((String) o)));
				} catch (Exception e) {
					return null;
				}
			}
			
			return null;
		}
		
		Object b = arguments.get(1);
		
		if (LuaType.getTypeOf(b) != LuaType.NUMBER || (int) b >= 36 || (int) b <= 2) {
			throw new LuaRuntimeException("base expected as Number between 2 and 36");
		}
		
		if (LuaType.getTypeOf(o) == LuaType.NUMBER) {
			list.add(Double.toString((double) o));
			list.add(b);
			
			return this.apply(list);
		}
		
		if (LuaType.getTypeOf(o) != LuaType.STRING) {
			return null;
		}
		
		list.add(new Double(Integer.parseInt((String) o, (int) b)));
		return list;
=======
	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER }, { null, LuaType.NUMBER } };

	// LuaType[][] expectedTypesOne = { { LuaType.STRING, LuaType.NUMBER } };
	// LuaType[][] expectedTypesTwo = { { LuaType.STRING, LuaType.NUMBER }, {
	// LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("tonumber", arguments, expectedTypes);

		List<Object> list = new LinkedList<Object>();
		Object o = arguments.get(0);

		if (arguments.size() == 1) {
			// Preconditions.checkArguments("tonumber", arguments,
			// expectedTypesOne);

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
			// Preconditions.checkArguments("tonumber", arguments,
			// expectedTypesTwo);

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
				Integer i = Integer.parseInt((String) o, b.intValue());
				list.add(new Double(i.intValue()));
			} catch (NumberFormatException e) {
				list.add(null);
			}
			return list;
		}
>>>>>>> Parser
	}
}
