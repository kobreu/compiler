package edu.tum.lua;

import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.BooleanExp;
import edu.tum.lua.ast.Closure;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.Field;
import edu.tum.lua.ast.FieldExp;
import edu.tum.lua.ast.FieldLRExp;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.FuncCall;
import edu.tum.lua.ast.Nil;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.PreExp;
import edu.tum.lua.ast.PrefixExp;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.Stat;
import edu.tum.lua.ast.SyntaxNode;
import edu.tum.lua.ast.TableConstructorExp;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.operator.Operator;
import edu.tum.lua.operator.OperatorRegistry;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class ExpVisitor extends VisitorAdaptor {

	private final Environment environment;

	private Deque<Object> evaluationStack;

	public ExpVisitor(Environment e) {
		environment = e;
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
	public void visit(Closure exp) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void visit(Dots exp) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void visit(FuncCall call) {
		call.preexp.accept(this);

		Deque<Object> args = findCallHandler(evaluationStack.removeLast());
		LuaFunction f = (LuaFunction) args.removeFirst();

		ExpVisitor visitor = new ExpVisitor(environment);
		Enumeration<Exp> iterator = call.explist.elements();

		while (iterator.hasMoreElements()) {
			iterator.nextElement().accept(this);
			List<Object> explist = visitor.popAll();

			if (iterator.hasMoreElements()) {
				if (!explist.isEmpty()) {
					args.addLast(explist.get(0));
				}
			} else {
				args.addAll(explist);
			}
		}

		List<Object> result = f.apply(args);

		SyntaxNode callParent = call.getParent();

		// Discard everything
		if (callParent instanceof Stat) {
			return;
		}

		if (callParent.getParent().getParent() instanceof ExpList) {
			PreExp preExp = (PreExp) callParent.getParent();
			ExpList list = (ExpList) preExp.getParent();

			if (list.elementAt(list.size() - 1) == preExp) {
				evaluationStack.addAll(f.apply(args));
				return;
			}
		}

		if (!result.isEmpty()) {
			evaluationStack.add(result.get(0));
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
	public void visit(PrefixExpVar expVar) {
		expVar.childrenAccept(this);
	}

	@Override
	public void visit(TableConstructorExp exp) {

		Enumeration<Field> fieldlist = exp.tablecons.fieldlist.elements();

		double keyindex = 0.0; // Automatic index
		LuaTable table = new LuaTable();

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

		evaluationStack.addLast(table);

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
	public void visit(Variable variable) {
		evaluationStack.addLast(environment.get(variable.var));
	}

	private Deque<Object> findCallHandler(Object object) {
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
}
