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
