package edu.tum.lua;

import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.BooleanExp;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.Field;
import edu.tum.lua.ast.FieldExp;
import edu.tum.lua.ast.FieldLRExp;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.FuncCall;
import edu.tum.lua.ast.FuncCallSelf;
import edu.tum.lua.ast.FunctionExp;
import edu.tum.lua.ast.LegacyAdapter;
import edu.tum.lua.ast.Nil;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.PreExp;
import edu.tum.lua.ast.PrefixExp;
import edu.tum.lua.ast.PrefixExpExp;
import edu.tum.lua.ast.PrefixExpFuncCall;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.Stat;
import edu.tum.lua.ast.SyntaxNode;
import edu.tum.lua.ast.TableConstructor;
import edu.tum.lua.ast.TableConstructorExp;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.VarTabIndex;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.exceptions.LuaStackTraceElement;
import edu.tum.lua.operator.Operator;
import edu.tum.lua.operator.OperatorRegistry;
import edu.tum.lua.operator.logical.LogicalOperatorSupport;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

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
		evaluationStack.addLast(operator.apply(op1, op2));
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

	private List<Object> call(Object object, ExpList args) {
		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		args.accept(visitor);
		return call(object, visitor.popAll());
	}

	private List<Object> call(Object object, String name, List<Object> args) {
		Deque<Object> argsPrefix = findCallHandler(object, name);
		LuaFunction f = (LuaFunction) argsPrefix.removeFirst();
		argsPrefix.add(object);
		argsPrefix.addAll(args);
		return f.apply(argsPrefix);
	}

	private List<Object> call(Object object, String name, ExpList args) {
		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		args.accept(visitor);
		return call(object, name, visitor.popAll());
	}

	@Override
	public void visit(FuncCall call) {
		call.preexp.accept(this);

		try {
			List<Object> result = call(evaluationStack.removeLast(), call.explist);

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
			if (ex.getLocation() == null) {
				// System.out.println("TODO Remove, Message: " + ex.getMessage()
				// + ex.getClass());
				// ex.setLocation(call);
				throw ex;
			}

			String name = "?";

			if (call.preexp instanceof PrefixExpVar) {
				PrefixExpVar var = (PrefixExpVar) call.preexp;

				if (var.var instanceof Variable) {
					name = ((Variable) var.var).var;
				}
			}

			ex.addLuaStackTraceElement(new LuaStackTraceElement(call, name, Collections.emptyList()));
			throw ex;
		}
	}

	@Override
	public void visit(FuncCallSelf call) {
		call.preexp.accept(this);

		try {
			List<Object> result = call(evaluationStack.removeLast(), call.name, call.explist);

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
			if (ex.getLocation() == null) {
				ex.setLocation(call);
				throw ex;
			}

			ex.addLuaStackTraceElement(new LuaStackTraceElement(call, call.name, Collections.emptyList()));
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
		evaluationStack.addLast(operator.apply(op));
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
			result = new LinkedList<Object>();
			result.add(res);
			return result;

		default:
			throw new LuaRuntimeException("attempt to index a " + LuaType.getTypeOf(object) + " value");
		}
	}
}
