package edu.tum.lua.stdlib;

import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class ToNumber extends LuaFunctionNative {

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
	}
}
