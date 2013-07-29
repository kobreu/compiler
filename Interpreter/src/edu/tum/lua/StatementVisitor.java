package edu.tum.lua;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import edu.tum.lua.ast.Asm;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.DoExp;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.ForExp;
import edu.tum.lua.ast.ForIn;
import edu.tum.lua.ast.FuncCallStmt;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.IfThenElse;
import edu.tum.lua.ast.LegacyAdapter;
import edu.tum.lua.ast.LocalDecl;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.Name;
import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.RepeatUntil;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.ast.WhileExp;
import edu.tum.lua.operator.logical.LogicalOperatorSupport;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class StatementVisitor extends VisitorAdaptor {

	private LocalEnvironment environment;
	private final List<Object> vararg;

	public StatementVisitor(LocalEnvironment e) {
		this(e, null);
	}

	public StatementVisitor(LocalEnvironment e, List<Object> v) {
		this.environment = e;
		vararg = v;
	}

	public LocalEnvironment getEnvironment() {
		return environment;
	}

	@Override
	public void visit() {
		throw new RuntimeException("Unsupported Statement");
	}

	public void assign(boolean local, List<String> identifiers, ExpList expList) {
		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		expList.accept(visitor);

		Iterator<String> identifierIterator = identifiers.iterator();
		Iterator<Object> valuesIterator = visitor.popAll().iterator();

		while (identifierIterator.hasNext()) {
			String identifier = identifierIterator.next();
			Object value = valuesIterator.hasNext() ? valuesIterator.next() : null;

			if (local) {
				environment.setLocal(identifier, value);
			} else {
				environment.set(identifier, value);
			}
		}
	}

	@Override
	public void visit(Asm stmt) {
		assign(false, LegacyAdapter.convert(stmt.varlist), stmt.explist);
	}

	@Override
	public void visit(DoExp stmt) {
		executeBlock(stmt.block);
	}

	@Override
	public void visit(ForExp stmt) {

		/*-
		 * Lua Pseudocode:
		 * 
		 * for v = e1, e2, e3 do block end
		 * 
		 * is equivalent to the code:
		 * 
		 * do
		 *   local var, limit, step = tonumber(e1), tonumber(e2), tonumber(e3)
		 *   if not (var and limit and step) then error() end
		 * 	 while (step > 0 and var <= limit) or (step <= 0 and var >= limit) do
		 * 	   local v = var block
		 * 	   var = var + step
		 * 	 end
		 * end // @formatter:on
		 */

		String v = stmt.ident;
		double var, limit, step;
		ExpVisitor visitor = new ExpVisitor(environment, vararg);

		stmt.start.accept(visitor);
		var = (double) visitor.popLast();
		stmt.end.accept(visitor);
		limit = (double) visitor.popLast();
		stmt.step.accept(visitor);
		step = (double) visitor.popLast();

		while ((step > 0.0 && var <= limit) || step <= 0 && var >= limit) {

			System.out.println("Visit: " + var + " ident " + v);
			// TODO Create a new local environment for v!
			LocalEnvironment localenv = new LocalEnvironment(environment);
			localenv.setLocal(v, var);
			// environment.set(v, var);
			LuaInterpreter.eval(stmt.block, localenv);
			var += step;
		}
	}

	@Override
	public void visit(ForIn stmt) {
		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		List<String> nl = LegacyAdapter.convert(stmt.namelist);
		stmt.explist.accept(visitor);
		List<Object> expl = visitor.popAll();
		System.out.println("size ExpList: " + expl.size());
		LuaFunction func = (LuaFunction) expl.get(0);
		Object state = expl.get(1);
		Object var = expl.get(2);
		System.out.println("f: " + func.toString() + ", s: " + state + ", v: " + var);

		while (true) {
			LocalEnvironment localEnvironment = new LocalEnvironment(environment);

			List<Object> results = ExpVisitor.call(func, Arrays.asList(state, var));
			System.out.println("result: " + results);
			int lastExpIndex = results.size() - 1;
			Object resultValue = null;

			for (int i = 0; i < nl.size(); i++) {
				resultValue = (i <= lastExpIndex) ? results.get(i) : null;
				System.out.println(resultValue);
				System.out.println(nl.get(i));
				localEnvironment.setLocal(nl.get(i), resultValue);
			}

			var = localEnvironment.get(nl.get(0));
			System.out.println("var: " + var);
			if (LuaType.getTypeOf(var) == LuaType.NIL) {
				break;
			}

			LuaInterpreter.eval(stmt.block, localEnvironment);
		}
	}

	@Override
	public void visit(FuncCallStmt stmt) {
		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		stmt.call.accept(visitor);
	}

	private LuaTable getFunctionLocation(NameList list) {
		Enumeration<Name> names = list.elements();
		if (!names.hasMoreElements()) {
			return environment.getGlobalEnvironment();
		}

		LuaTable current = (LuaTable) environment.get(names.nextElement().name);

		while (names.hasMoreElements()) {
			current = current.getLuaTable(names.nextElement().name);
		}

		return current;
	}

	@Override
	public void visit(FunctionDef stmt) {
		LuaFunction function = new LuaFunctionInterpreted(stmt, environment);
		LuaTable target = getFunctionLocation(stmt.members);
		target.set(stmt.ident, function);
	}

	@Override
	public void visit(IfThenElse stmt) {
		if (isTrue(stmt.ifexp)) {
			executeBlock(stmt.thenblock);
		} else {
			executeBlock(stmt.elseblock);
		}
	}

	@Override
	public void visit(LocalDecl stmt) {
		environment = new LocalEnvironment(environment);
		assign(true, LegacyAdapter.convert(stmt.namelist), stmt.explist);
	}

	@Override
	public void visit(LocalFuncDef stmt) {
		LocalEnvironment oldLocalEnvironment = environment;

		/* Enable recursive calls */
		oldLocalEnvironment.setLocal(stmt.ident, null);
		LuaFunction function = new LuaFunctionInterpreted(stmt, oldLocalEnvironment);
		oldLocalEnvironment.setLocal(stmt.ident, function);

		environment = new LocalEnvironment(oldLocalEnvironment);
	}

	@Override
	public void visit(RepeatUntil stmt) {
		do {
			executeBlock(stmt.block);
		} while (!isTrue(stmt.exp));
	}

	@Override
	public void visit(WhileExp stmt) {
		while (isTrue(stmt.exp)) {
			executeBlock(stmt.block);
		}
	}

	private void executeBlock(Block block) {
		LocalEnvironment blockEnvironment = new LocalEnvironment(environment);
		LuaInterpreter.eval(block, blockEnvironment);
	}

	private boolean isTrue(Exp exp) {
		ExpVisitor visitor = new ExpVisitor(environment, vararg);
		exp.accept(visitor);
		return LogicalOperatorSupport.isTrue(visitor.popLast());
	}
}
