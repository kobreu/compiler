package edu.tum.lua.operator.list;

<<<<<<< HEAD
import edu.tum.lua.operator.Operator;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaFunction;

public class LengthOperator extends Operator{
	
	public Object apply(Object op) throws NoSuchMethodException{
		if (LuaType.getTypeOf(op)==LuaType.STRING) {
			return op.toString().length();
		}
		else if (LuaType.getTypeOf(op)==LuaType.TABLE){
			return applyTable((LuaTable) op);
		}
		else {
			LuaFunction handler = getHandler("length",op);
			return handler.apply(op);
		}
	}
	
	protected Object applyTable(LuaTable op){
		double value = 0;
		while (op.get(value+1) != null){
			value++;
		}
		return value;
	}
=======
import edu.tum.lua.operator.UnaryOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class LengthOperator extends UnaryOperator {

	@Override
	public Object apply(Object op) {
		switch (LuaType.getTypeOf(op)) {
		case STRING:
			return new Double(((String) op).length());

		case TABLE:
			double value = 0;
			while (((LuaTable) op).get(value + 1) != null) {
				value++;
			}
			return value;

		default:
			LuaFunction handler = getHandler("__len", op);
			return handler.apply(op);
		}
	}
>>>>>>> Parser

}
