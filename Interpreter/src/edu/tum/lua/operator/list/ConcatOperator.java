package edu.tum.lua.operator.list;

import javax.naming.OperationNotSupportedException;

import edu.tum.lua.operator.Operator;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;

public class ConcatOperator extends Operator{
	
	protected static String convert(Object object) throws OperationNotSupportedException{
		if (LuaType.getTypeOf(object) == LuaType.STRING){
			return (String) object;
		}
		else if (LuaType.getTypeOf(object)==LuaType.NUMBER){
			return ((Double) object).toString();
		}
		throw new OperationNotSupportedException();
	}
	
	
	public Object apply(Object op1, Object op2) throws NoSuchMethodException{
		try {return applyString(convert(op1),convert(op2));}
		catch(OperationNotSupportedException e) {
			LuaFunction handler = getHandler("concat", op1,op2);
			return handler.apply(op1,op2);
		}

	}
	
	protected String applyString(String op1,String op2){
		return op1.concat(op2);
	}

}
