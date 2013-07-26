package edu.tum.lua;

import static edu.tum.lua.LuaInterpreter.eval;

import java.util.Enumeration;
import java.util.List;

import edu.tum.lua.ast.Asm;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.DoExp;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ForExp;
import edu.tum.lua.ast.ForIn;
import edu.tum.lua.ast.FuncCallStmt;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.IfThenElse;
import edu.tum.lua.ast.LegacyAdapter;
import edu.tum.lua.ast.LocalDecl;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.RepeatUntil;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.ast.WhileExp;
import edu.tum.lua.operator.logical.LogicalOperatorSupport;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaFunctionInterpreted;
import edu.tum.lua.types.LuaTable;

public class StatementVisitor extends VisitorAdaptor {

	private LocalEnvironment environment;

	public StatementVisitor(LocalEnvironment e) {
		this.environment = e;
	}

	public LocalEnvironment getEnvironment() {
		return environment;
	}

	@Override
	public void visit() {
		throw new RuntimeException("Unsupported Statement");
	}

	@Override
	public void visit(Asm stmt) {
		List<String> varlist = LegacyAdapter.convert(stmt.varlist);
		List<Exp> explist = LegacyAdapter.convert(stmt.explist);
		environment.assign(varlist, explist);
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
		ExpVisitor visitor = new ExpVisitor(environment);

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

	}

	@Override
	public void visit(FuncCallStmt stmt) {
		ExpVisitor visitor = new ExpVisitor(environment);
		stmt.call.accept(visitor);
	}

	private LuaTable getFunctionLocation(NameList list) {
		Enumeration<String> names = list.elements();

		LuaTable current = (LuaTable) environment.get(names.nextElement());

		while (names.hasMoreElements()) {
			current = current.getLuaTable(names.nextElement());
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
		List<String> varlist = LegacyAdapter.convert(stmt.namelist);

		for (String localVar : varlist) {
			environment.setLocal(localVar, null);
		}

		List<Exp> explist = LegacyAdapter.convert(stmt.explist);
		environment.assign(varlist, explist);
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
		return LogicalOperatorSupport.isTrue(eval(exp, environment));
	}
}
