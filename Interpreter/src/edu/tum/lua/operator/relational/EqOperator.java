package edu.tum.lua.operator.relational;

<<<<<<< HEAD
import edu.tum.lua.operator.Operator;
import edu.tum.lua.operator.logical.LogicalOperator;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class EqOperator extends Operator {

=======
import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.operator.BinaryOperator;
import edu.tum.lua.operator.logical.LogicalOperatorSupport;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaType;

public class EqOperator extends BinaryOperator {

	@Override
>>>>>>> Parser
	public Boolean apply(Object op1, Object op2) {
		if (LuaType.getTypeOf(op1) != LuaType.getTypeOf(op2)) {
			return false;
		}

		if (op1 == op2) {
			return true;
		}

		switch (LuaType.getTypeOf(op1)) {
		case BOOLEAN:
		case NUMBER:
		case STRING:
			return op1.equals(op2);

		case FUNCTION:
			return false;

		case TABLE:
			try {
				LuaFunction handler1 = getHandler(handlerName(), op1);
				LuaFunction handler2 = getHandler(handlerName(), op2);
<<<<<<< HEAD
				
				if (handler1 == handler2) {
					return LogicalOperator.isTrue(handler1.apply(op1, op2).get(0));
				}
			} catch (NoSuchMethodException e) {
				return false;
			}
		
=======

				if (handler1 == handler2) {
					return LogicalOperatorSupport.isTrue(handler1.apply(op1, op2).get(0));
				}
			} catch (LuaRuntimeException ex) {
				return false;
			}

>>>>>>> Parser
		default:
			throw new IllegalStateException();
		}
	}

	protected String handlerName() {
<<<<<<< HEAD
		return "eq";
=======
		return "__eq";
>>>>>>> Parser
	}
}
