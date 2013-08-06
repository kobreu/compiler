package edu.tum.juna;

import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import edu.tum.juna.ast.Binop;
import edu.tum.juna.ast.BooleanExp;
import edu.tum.juna.ast.Dots;
import edu.tum.juna.ast.Exp;
import edu.tum.juna.ast.ExpList;
import edu.tum.juna.ast.Field;
import edu.tum.juna.ast.FieldExp;
import edu.tum.juna.ast.FieldLRExp;
import edu.tum.juna.ast.FieldNameExp;
import edu.tum.juna.ast.FuncCall;
import edu.tum.juna.ast.FuncCallSelf;
import edu.tum.juna.ast.FunctionExp;
import edu.tum.juna.ast.LegacyAdapter;
import edu.tum.juna.ast.Nil;
import edu.tum.juna.ast.NumberExp;
import edu.tum.juna.ast.Op;
import edu.tum.juna.ast.PreExp;
import edu.tum.juna.ast.PrefixExp;
import edu.tum.juna.ast.PrefixExpExp;
import edu.tum.juna.ast.PrefixExpFuncCall;
import edu.tum.juna.ast.PrefixExpVar;
import edu.tum.juna.ast.Stat;
import edu.tum.juna.ast.SyntaxNode;
import edu.tum.juna.ast.TableConstructor;
import edu.tum.juna.ast.TableConstructorExp;
import edu.tum.juna.ast.TextExp;
import edu.tum.juna.ast.Unop;
import edu.tum.juna.ast.VarTabIndex;
import edu.tum.juna.ast.Variable;
import edu.tum.juna.ast.VisitorAdaptor;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.exceptions.LuaStackTraceElement;
import edu.tum.juna.operator.Operator;
import edu.tum.juna.operator.OperatorRegistry;
import edu.tum.juna.operator.logical.LogicalOperatorSupport;
import edu.tum.juna.types.LuaFunction;
import edu.tum.juna.types.LuaFunctionInterpreted;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class ExpVisitor extends VisitorAdaptor {

	private final LocalEnvironment environment;
	private final List<Object> vararg;

	private Deque<Object> evaluationStack;

	public ExpVisitor(LocalEnvironment e, List<Object> v) {
		environment = e;
		vararg = v;
		evaluationStack = new LinkedList<>();
	}

	public List<Object> popAll() {
		List<Object> stack = new LinkedList<>(evaluationStack);
		evaluationStack = new LinkedList<>();
		return stack;
	}

	public Object popLast() {
		if (evaluationStack.size() > 1) {
			throw new IllegalStateException();
		}

		return evaluationStack.removeLast();
	}

	@Override
	public void visit() {
		throw new RuntimeException("Missing implementation");
	}

	@Override
	public void visit(Binop binop) {
		binop.leftexp.accept(this);
		Object op1 = evaluationStack.removeLast();

		if (binop.op == Op.AND && !LogicalOperatorSupport.isTrue(op1)) {
			evaluationStack.addLast(op1);
			return;
		}

		if (binop.op == Op.OR && LogicalOperatorSupport.isTrue(op1)) {
			evaluationStack.addLast(op1);
			return;
		}

		binop.rightexp.accept(this);
		Object op2 = evaluationStack.removeLast();

		Operator operator = OperatorRegistry.registry[binop.op];

		try {
			evaluationStack.addLast(operator.apply(op1, op2));
		} catch (LuaRuntimeException ex) {
			ex.offerSyntaxNode(binop);
			throw ex;
		}
	}

	@Override
	public void visit(BooleanExp exp) {
		evaluationStack.addLast(exp.value);
	}

	@Override
	public void visit(FunctionExp exp) {
		LuaFunction function = new LuaFunctionInterpreted(LegacyAdapter.convert(exp.args), exp.varargs, exp.block,
				environment);
		evaluationStack.add(function);
	}

	@Override
	public void visit(Dots exp) {
		if (vararg == null) {
			throw new LuaRuntimeException(exp, "cannot use '...' outside a vararg function");
		}

		// Adjust vararg

		if (vararg.isEmpty()) {
			evaluationStack.addLast(null);
			return;
		}

		if (exp.getParent() instanceof ExpList) {
			ExpList list = (ExpList) exp.getParent();
			if (list.elementAt(list.size() - 1) == exp) {
				evaluationStack.addAll(vararg);
				return;
			}
		}

		evaluationStack.addLast(vararg.get(0));
	}

	@Override
	public void visit(ExpList expl) {
		for (Exp e : LegacyAdapter.convert(expl)) {
			e.accept(this);
		}
	}

	public static List<Object> call(Object object, List<Object> args) {
		Deque<Object> argsPrefix = findCallHandler(object);
		LuaFunction f = (LuaFunction) argsPrefix.removeFirst();
		argsPrefix.addAll(args);
		return f.apply(argsPrefix);
	}

	private List<Object> call(Object object, String name, List<Object> args) {
		Deque<Object> argsPrefix = findCallHandler(object, name);
		LuaFunction f = (LuaFunction) argsPrefix.removeFirst();
		argsPrefix.add(object);
		argsPrefix.addAll(args);
		return f.apply(argsPrefix);
	}

	@Override
	public void visit(FuncCall call) {
		call.preexp.accept(this);

		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		call.explist.accept(visitor);
		List<Object> args = visitor.popAll();

		try {
			List<Object> result = call(evaluationStack.removeLast(), args);

			SyntaxNode callParent = call.getParent();

			// Discard everything
			if (callParent instanceof Stat) {
				return;
			}

			if (callParent.getParent().getParent() instanceof ExpList) {
				PreExp preExp = (PreExp) callParent.getParent();
				ExpList list = (ExpList) preExp.getParent();

				if (list.elementAt(list.size() - 1) == preExp) {
					evaluationStack.addAll(result);
					return;
				}
			}

			if (!result.isEmpty()) {
				evaluationStack.add(result.get(0));
			}
		} catch (LuaRuntimeException ex) {
			ex.offerSyntaxNode(call);

			String name = "?";

			if (call.preexp instanceof PrefixExpVar) {
				PrefixExpVar var = (PrefixExpVar) call.preexp;

				if (var.var instanceof Variable) {
					name = ((Variable) var.var).var;
				}
			}

			ex.addLuaStackTraceElement(new LuaStackTraceElement(call, name, args));
			throw ex;
		}
	}

	@Override
	public void visit(FuncCallSelf call) {
		call.preexp.accept(this);

		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		call.explist.accept(visitor);
		List<Object> args = visitor.popAll();

		try {
			List<Object> result = call(evaluationStack.removeLast(), call.name, args);

			SyntaxNode callParent = call.getParent();

			if (callParent instanceof Stat) {
				return;
			}

			if (callParent.getParent().getParent() instanceof ExpList) {
				PreExp preExp = (PreExp) callParent.getParent();
				ExpList list = (ExpList) preExp.getParent();

				if (list.elementAt(list.size() - 1) == preExp) {
					evaluationStack.addAll(result);
					return;
				}
			}

			if (!result.isEmpty()) {
				evaluationStack.add(result.get(0));
			}

		} catch (LuaRuntimeException ex) {
			ex.offerSyntaxNode(call);
			ex.addLuaStackTraceElement(new LuaStackTraceElement(call, call.name, args));
			throw ex;
		}
	}

	@Override
	public void visit(Nil exp) {
		evaluationStack.addLast(null);
	}

	@Override
	public void visit(NumberExp exp) {
		evaluationStack.addLast(exp.number);
	}

	@Override
	public void visit(PreExp exp) {
		exp.preexp.accept(this);
	}

	@Override
	public void visit(PrefixExp exp) {
		exp.accept(this);
	}

	@Override
	public void visit(PrefixExpExp exp) {
		exp.exp.accept(this);
	}

	@Override
	public void visit(PrefixExpVar expVar) {
		expVar.var.accept(this);
	}

	@Override
	public void visit(PrefixExpFuncCall exp) {
		exp.call.accept(this);
	}

	@Override
	public void visit(TableConstructorExp exp) {
		TableConstructor tableConstructor = exp.tablecons;

		LuaTable table = new LuaTable();
		evaluationStack.addLast(table);

		if (tableConstructor.fieldlist == null) {
			return;
		}

		Enumeration<Field> fieldlist = exp.tablecons.fieldlist.elements();

		double keyindex = 0.0; // Automatic index

		while (fieldlist.hasMoreElements()) {
			Field field = fieldlist.nextElement();

			if (field instanceof FieldExp) {

				keyindex++;
				((FieldExp) field).fieldexp.accept(this);
				Object value = evaluationStack.removeLast();
				table.set(keyindex, value);

			} else if (field instanceof FieldLRExp) {

				((FieldLRExp) field).leftexp.accept(this);
				Object key = evaluationStack.removeLast();

				((FieldLRExp) field).rightexp.accept(this);
				Object value = evaluationStack.removeLast();

				// This is nasty!
				if (key instanceof Double && (double) key <= keyindex) {
					// This key has been set by automatic keyindex, so do
					// not do anything.
				} else {
					table.set(key, value);
				}

			} else if (field instanceof FieldNameExp) {

				String key = ((FieldNameExp) field).ident;

				((FieldNameExp) field).exp.accept(this);
				Object value = evaluationStack.removeLast();

				table.set(key, value);
			}
		}

	}

	@Override
	public void visit(TextExp exp) {
		evaluationStack.addLast(exp.text);
	}

	@Override
	public void visit(Unop unop) {
		unop.exp.accept(this);
		Object op = evaluationStack.removeLast();

		Operator operator = OperatorRegistry.registry[unop.op];

		try {
			evaluationStack.addLast(operator.apply(op));
		} catch (LuaRuntimeException ex) {
			ex.offerSyntaxNode(unop);
			throw ex;
		}
	}

	@Override
	public void visit(VarTabIndex varTabIndex) {
		varTabIndex.preexp.accept(this);

		if (LuaType.getTypeOf(evaluationStack.peekLast()) != LuaType.TABLE) {
			throw new LuaRuntimeException(varTabIndex, "attempt to index a "
					+ LuaType.getTypeOf(evaluationStack.peekLast()));
		}

		LuaTable table = (LuaTable) evaluationStack.removeLast();
		varTabIndex.indexexp.accept(this);
		Object key = evaluationStack.removeLast();
		evaluationStack.addLast(table.get(key));
	}

	@Override
	public void visit(Variable variable) {
		evaluationStack.addLast(environment.get(variable.var));
	}

	static private Deque<Object> findCallHandler(Object object) {
		Deque<Object> result;

		switch (LuaType.getTypeOf(object)) {
		case FUNCTION:
			result = new LinkedList<>();
			result.add(object);
			return result;

			/*
			 * meta.__call(object, ...) - metafunctions take the table as first
			 * argument!
			 */
		case TABLE:
			LuaTable meta = ((LuaTable) object).getMetatable();

			if (meta != null) {
				result = findCallHandler(meta.get("__call"));
				result.add(object);
				return result;
			}

		default:
			throw new LuaRuntimeException("attempt to call a " + LuaType.getTypeOf(object) + " value");
		}
	}

	static private Deque<Object> findCallHandler(Object object, String name) {
		Deque<Object> result;
		switch (LuaType.getTypeOf(object)) {
		case TABLE:
			Object res = ((LuaTable) object).get(name);
			if (!(res instanceof LuaFunction)) {
				throw new LuaRuntimeException("attempt to call method " + name + " (a" + LuaType.getTypeOf(res)
						+ " value)");
			}
			result = new LinkedList<>();
			result.add(res);
			return result;

		default:
			throw new LuaRuntimeException("attempt to index a " + LuaType.getTypeOf(object) + " value");
		}
	}
}
