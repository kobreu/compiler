package edu.tum.lua;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.BooleanExp;
import edu.tum.lua.ast.Closure;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.FunctionCall;
import edu.tum.lua.ast.LegacyAdapter;
import edu.tum.lua.ast.Nil;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.PrefixExp;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.TableConstructorExp;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.operator.arithmetic.AddOperator;
import edu.tum.lua.types.LuaFunction;

public class ExpVisitor extends VisitorAdaptor {

	private final Deque<Object> evaluationStack;

	private final Environment environment;

	public ExpVisitor(Environment e) {
		environment = e;
		evaluationStack = new LinkedList<>();
	}

	public Object getReturn() {
		if (evaluationStack.size() > 1) {
			throw new IllegalStateException();
		}

		return evaluationStack.getLast();
	}

	@Override
	public void visit() {
		throw new RuntimeException("Missing implementation");
	}

	@Override
	public void visit(Nil exp) {
		evaluationStack.addLast(null);
	}

	@Override
	public void visit(BooleanExp exp) {
		evaluationStack.addLast(exp.value);
	}

	@Override
	public void visit(NumberExp exp) {
		evaluationStack.addLast(exp.number);
	}

	@Override
	public void visit(TextExp exp) {
		evaluationStack.addLast(exp.text);
	}

	@Override
	public void visit(Dots exp) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void visit(Closure exp) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void visit(PrefixExp exp) {
		// NOP
	}

	@Override
	public void visit(TableConstructorExp exp) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void visit(Binop binop) {
		// Reverse stack order!
		Object op2 = evaluationStack.removeLast();
		Object op1 = evaluationStack.removeLast();
		Object result;

		switch (binop.op) {
		case Op.ADD:
			// FIXME: Create Registry for Operators
			AddOperator add = new AddOperator();
			try {
				result = add.apply(op1, op2);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new LuaRuntimeException("missing handler");
			}
			break;

		default:
			throw new RuntimeException("Not yet implemented");
		}

		evaluationStack.addLast(result);
	}

	@Override
	public void visit(Unop unop) {
		Object op1 = evaluationStack.removeLast();
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void visit(PrefixExpVar expVar) {

	}

	private Object doFunctionCall(FunctionCall call) {
		PrefixExp prefixExp = call.preexp;

		/* Get LuaFunction */
		LuaFunction function;
		Object f;

		if (prefixExp instanceof PrefixExpVar) {
			PrefixExpVar var = (PrefixExpVar) prefixExp;
		}

		List<Exp> argsExp = LegacyAdapter.convert(call.explist);

		/* __call methametod -> */

		return null;
	}
}
