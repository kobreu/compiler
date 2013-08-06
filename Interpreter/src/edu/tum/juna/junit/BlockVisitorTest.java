package edu.tum.juna.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import edu.tum.juna.BlockVisitor;
import edu.tum.juna.LocalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Asm;
import edu.tum.juna.ast.Binop;
import edu.tum.juna.ast.Block;
import edu.tum.juna.ast.DoExp;
import edu.tum.juna.ast.ExpList;
import edu.tum.juna.ast.IfThenElse;
import edu.tum.juna.ast.LastBreak;
import edu.tum.juna.ast.LocalDecl;
import edu.tum.juna.ast.Name;
import edu.tum.juna.ast.NameList;
import edu.tum.juna.ast.NumberExp;
import edu.tum.juna.ast.Op;
import edu.tum.juna.ast.PreExp;
import edu.tum.juna.ast.PrefixExpVar;
import edu.tum.juna.ast.StatList;
import edu.tum.juna.ast.VarList;
import edu.tum.juna.ast.Variable;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.types.LuaTable;
import edu.tum.juna.types.LuaType;

public class BlockVisitorTest {

	private BlockVisitor visitor;
	private LocalEnvironment environment;

	@Before
	public void setUp() throws Exception {
		environment = new LocalEnvironment();
		visitor = new BlockVisitor(environment);
	}

	@Test
	public void testVisitAsm() {
		Asm asm = new Asm(new VarList(new Variable("a")), new ExpList(new Binop(new NumberExp(1.0), Op.ADD,
				new NumberExp(1.0))));

		assertEquals(null, environment.get("a"));
		visitor.visit(asm);
		assertEquals(2.0, environment.get("a"));
	}

	@Test
	public void testVisitFuncCallStmt() throws Exception {
		Block block = ParserUtil.loadString("a=1+2");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertEquals(3.0, environment.get("a"));

		block = ParserUtil.loadString("b=type(type(a))");

		assertEquals(null, environment.get("b"));
		LuaInterpreter.eval(block, environment);
		assertEquals("string", environment.get("b"));

		block = ParserUtil.loadString("c='a'");

		assertEquals(null, environment.get("c"));
		LuaInterpreter.eval(block, environment);
		assertEquals("a", environment.get("c"));

		block = ParserUtil.loadString("x=0 a={} a.foo=function() x=1 end a:foo()");
		setUp();
		LuaInterpreter.eval(block, environment);
		assertEquals(1.0, environment.get("x"));
	}

	@Test
	public void testVisitFuncCallStmt_OneParam() throws Exception {
		Block block1 = ParserUtil.loadString("foo = function(x) a=x end");
		Block block2 = ParserUtil.loadString("foo(1)");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block1, environment);
		LuaInterpreter.eval(block2, environment);
		assertEquals(1.0, environment.get("a"));
	}

	@Test
	public void testVisitFuncCallStmt_TwoParams() throws Exception {
		Block block1 = ParserUtil.loadString("foo = function(x,y) a=x; b=y end");
		Block block2 = ParserUtil.loadString("foo(1,2)");

		assertEquals(null, environment.get("a"));
		assertEquals(null, environment.get("b"));
		LuaInterpreter.eval(block1, environment);
		LuaInterpreter.eval(block2, environment);
		assertEquals(1.0, environment.get("a"));
		assertEquals(2.0, environment.get("b"));
	}

	@Test
	public void testVisitIfThenElse() throws Exception {
		Block block = ParserUtil.loadString("a=false if a then b=2 elseif a then b=3 else b=1 end");
		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertFalse((boolean) environment.get("a"));
		assertEquals(1.0, environment.get("b"));
	}

	@Test
	public void testVisitIf() throws Exception {

		environment = new LocalEnvironment();
		visitor = new BlockVisitor(environment);

		// > a = 1+1 >> a=2 // > if a == 2 then b=1 else b=0 end >> b=1
		// > if b!= 1 then c=1 else c=0 end >> c=0

		Asm asm = new Asm(new VarList(new Variable("a")), new ExpList(new Binop(new NumberExp(1.0), Op.ADD,
				new NumberExp(1.0))));

		// > a = 1+1 assertEquals(null, environment.get("a"));
		visitor.visit(asm);
		assertEquals(2.0, environment.get("a"));

		// > if a == 2 then b=1 else b=0 end >> b=1
		assertEquals(null, environment.get("b"));
		IfThenElse ifstatement = new IfThenElse(new Binop(new PreExp(new PrefixExpVar(new Variable("a"))), Op.EQ,
				new NumberExp(2.0)), new Block(new StatList(new Asm(new VarList(new Variable("b")), new ExpList(
				new NumberExp(1.0)))), null), new Block(new StatList(new Asm(new VarList(new Variable("b")),
				new ExpList(new NumberExp(2.0)))), null));

		visitor = new BlockVisitor(environment);
		visitor.visit(ifstatement);
		assertEquals(1.0, environment.get("b"));

		// > if a == 2 then b=1 else b=0 end >> b=1

		Block block2 = ParserUtil.loadString("if b~=1 then c=1 else c=0 end");

		assertEquals(null, environment.get("c"));
		LuaInterpreter.eval(block2, environment);
		assertEquals(0.0, environment.get("c"));

	}

	@Test
	public void testVisitIf_noElseBlock() throws Exception {

		Block block = ParserUtil.loadString("a=1; if a==1 then b=1 end; if a~=1 then c=1 end");

		assertEquals(null, environment.get("a"));
		assertEquals(null, environment.get("b"));
		assertEquals(null, environment.get("c"));
		LuaInterpreter.eval(block, environment);
		assertEquals(1.0, environment.get("a"));
		assertEquals(1.0, environment.get("b"));
		assertEquals(null, environment.get("c"));

	}

	@Test
	public void testVisitDoExp() throws Exception {
		Block block = ParserUtil.loadString("a=1");
		DoExp doExp = new DoExp(block);

		assertEquals(null, environment.get("a"));
		visitor.visit(doExp);
		assertEquals(1.0, environment.get("a"));
	}

	@Test
	public void testVisitWhileExp() throws Exception {
		Block block = ParserUtil.loadString("a=1");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertEquals(1.0, environment.get("a"));

		block = ParserUtil.loadString("while a<4 do a=a+1 b=2 end");

		LuaInterpreter.eval(block, environment);
		assertEquals(4.0, environment.get("a"));
		assertEquals(2.0, environment.get("b"));
	}

	@Test
	public void testVisitRepeatUntil() throws Exception {
		Block block = ParserUtil.loadString("a='a'");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertEquals("a", environment.get("a"));

		block = ParserUtil.loadString("repeat a=a..'a' b=2 until a=='aaa'");

		LuaInterpreter.eval(block, environment);
		assertEquals("aaa", environment.get("a"));
		assertEquals(2.0, environment.get("b"));
	}

	@Test
	public void testVisitForExp_NormalIteration() throws Exception {

		Block block1, block2;

		block1 = ParserUtil.loadString("b=0");
		block2 = ParserUtil.loadString("for a=1,3 do b=a end");

		LuaInterpreter.eval(block1, environment);
		assertEquals(0.0, environment.get("b"));
		LuaInterpreter.eval(block2, environment);
		assertEquals(3.0, environment.get("b"));
	}

	@Test
	public void testVisitForExp_SteppedIteration() throws Exception {

		Block block1, block2;
		block1 = ParserUtil.loadString("b=0");
		block2 = ParserUtil.loadString("for a=1,2.9,0.5 do b=a end");

		assertEquals(null, environment.get("b"));
		LuaInterpreter.eval(block1, environment);
		assertEquals(0.0, environment.get("b"));
		LuaInterpreter.eval(block2, environment);
		assertEquals(2.5, environment.get("b"));

	}

	@Test
	public void testVisitForExp_NegativeIteration() throws Exception {

		Block block1, block2;
		block1 = ParserUtil.loadString("b=0");
		block2 = ParserUtil.loadString("for a = 3,1,-1 do b=a end");

		LuaInterpreter.eval(block1, environment);
		assertEquals(0.0, environment.get("b"));
		LuaInterpreter.eval(block2, environment);
		assertEquals(1.0, environment.get("b"));
	}

	@Test
	public void testVisitForExp_LocalVar() throws Exception {
		/*-
		 * Do not change "a"
		 * > a = 1
		 * > for a = 1,3 do b=a end
		 * > print(a,b) >> 1 3
		 */
		Block block1, block2;
		block1 = ParserUtil.loadString("a=1");
		block2 = ParserUtil.loadString("for a = 1,3 do b=a end");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block1, environment);
		assertEquals(1.0, environment.get("a"));
		LuaInterpreter.eval(block2, environment);
		assertEquals(1.0, environment.get("a"));
		assertEquals(3.0, environment.get("b"));
	}

	@Test
	public void testVisitForIn() throws Exception {

		Block block = ParserUtil.loadString("a='a'");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertEquals("a", environment.get("a"));

		LuaTable table = new LuaTable();
		table.set("one", 1.0);
		table.set("two", 2.0);
		table.set("three", 3.0);

		environment.set("t", table);
		LuaTable t = (LuaTable) environment.get("t");
		assertEquals(1.0, t.get("one"));
		assertEquals(2.0, t.get("two"));
		assertEquals(3.0, t.get("three"));

		block = ParserUtil.loadString("for k,v in pairs(t) do a=a..k end");
		LuaInterpreter.eval(block, environment);
		assertEquals("atwoonethree", environment.get("a"));
	}

	@Test
	public void testVisitFunctionDef() throws Exception {
		Block block = ParserUtil.loadString("a={}");

		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertEquals(LuaType.TABLE, LuaType.getTypeOf(environment.get("a")));

		block = ParserUtil.loadString("a.foo= function() end");
		LuaInterpreter.eval(block, environment);

		assertEquals(LuaType.FUNCTION, LuaType.getTypeOf(((LuaTable) environment.get("a")).get("foo")));
	}

	@Test
	public void testVisitLocalFuncDef() throws Exception {
		Block block = ParserUtil.loadString("x=0");
		assertEquals(null, environment.get("x"));
		LuaInterpreter.eval(block, environment);
		assertEquals(0.0, environment.get("x"));

		block = ParserUtil.loadString("local function foo() x=1 end foo()");

		LuaInterpreter.eval(block, environment);
		assertEquals(1.0, environment.get("x"));
	}

	@Test
	public void testVisitLocalDecl() throws Exception {
		// Block block =ParserUtil.loadString("local a = 1 x = a+1");
		LocalDecl l = new LocalDecl(new NameList(new Name("a")), new ExpList(new NumberExp(1.0)));
		Asm asm = new Asm(new VarList(new Variable("x")), new ExpList(new Binop(new NumberExp(1.0), Op.ADD, new PreExp(
				new PrefixExpVar(new Variable("a"))))));
		StatList statList = new StatList(l);
		statList.append(asm);
		Block block = new Block(statList, new LastBreak());
		assertEquals(null, environment.get("a"));
		LuaInterpreter.eval(block, environment);
		assertEquals(null, environment.get("a"));
		assertEquals(2.0, environment.get("x"));

	}

}
