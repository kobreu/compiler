package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.ExpVisitor;
import edu.tum.lua.LocalEnvironment;
import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.BooleanExp;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.FieldExp;
import edu.tum.lua.ast.FieldLRExp;
import edu.tum.lua.ast.FieldList;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.FunctionExp;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.Nil;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.PreExp;
import edu.tum.lua.ast.PrefixExpVar;
import edu.tum.lua.ast.StatList;
import edu.tum.lua.ast.TableConstructor;
import edu.tum.lua.ast.TableConstructorExp;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;

public class ExpVisitorTest {

	private ExpVisitor visitor;
	private LocalEnvironment environment;

	@Before
	public void setUp() throws Exception {
		environment = new LocalEnvironment();
		visitor = new ExpVisitor(environment, null);
	}

	@Test
	public void testVisitNil() {
		Exp exp = new Nil();
		exp.accept(visitor);
		assertEquals(null, visitor.popLast());
	}

	@Test
	public void testVisitBooleanExp() {
		Exp exp = new BooleanExp(true);
		exp.accept(visitor);
		assertEquals(true, visitor.popLast());
	}

	@Test
	public void testVisitNumberExp() {
		Exp exp = new NumberExp(-1.0);
		exp.accept(visitor);
		assertEquals(-1.0, visitor.popLast());
	}

	@Test
	public void testVisitTextExp() {
		Exp exp = new TextExp("a:b");
		exp.accept(visitor);
		assertEquals("a:b", visitor.popLast());
	}

	@Test
	public void testVisitDots() {
		LuaTable t = new LuaTable();
		visitor = new ExpVisitor(environment, Arrays.asList("a", 1.0, t));
		ExpList exp = new ExpList(new Dots());

		exp.accept(visitor);
		assertEquals(Arrays.asList("a", 1.0, t), visitor.popAll());

		exp.append(new TextExp("1.0"));
		exp.accept(visitor);
		assertEquals(Arrays.asList("a", "1.0"), visitor.popAll());
	}

	@Test
	public void testVisitFunctionExp() {
		Exp exp = new FunctionExp(new NameList(), true, new Block(new StatList(), new LastReturn(
				new ExpList(new Dots()))));

		exp.accept(visitor);
		LuaFunction function = (LuaFunction) visitor.popLast();

		assertEquals(java.util.Collections.singletonList("1.0"), function.apply("1.0"));

	}

	@Test
	public void testVisitTableConstructorExp() {

		// > t = {"one", "two", [3.0] = "three", x4 = "four", "5" = "five"}
		// > for k,v in pairs(t) do print(k,v) >> 1->"one", 2->"two"

		FieldList fieldlist = new FieldList(new FieldExp(new TextExp("one")));
		fieldlist.append(new FieldExp(new TextExp("two")));
		fieldlist.append(new FieldLRExp(new NumberExp(3.0), new TextExp("three")));
		fieldlist.append(new FieldNameExp("x4", new TextExp("four")));
		fieldlist.append(new FieldLRExp(new TextExp("5"), new TextExp("five")));
		TableConstructorExp tableconstructorexp = new TableConstructorExp(new TableConstructor(fieldlist));
		visitor.visit(tableconstructorexp);
		LuaTable table = (LuaTable) visitor.popLast();
		assertEquals("one", table.get(1.0));
		assertEquals("two", table.get(2.0));
		assertEquals("three", table.get(3.0));
		assertEquals("four", table.get("x4"));
		assertEquals("five", table.get("5"));

		// > t = {"one", "two", [1]="im lost"}
		// > for k,v in pairs(t) do print(k,v) >> 1->"one", 2->"two"
		environment = new LocalEnvironment();
		visitor = new ExpVisitor(environment, null);

		fieldlist = new FieldList(new FieldExp(new TextExp("one")));
		fieldlist.append(new FieldExp(new TextExp("two")));
		fieldlist.append(new FieldLRExp(new NumberExp(1.0), new TextExp("im lost")));
		visitor.visit(tableconstructorexp);
		table = (LuaTable) visitor.popLast();
		assertEquals("one", table.get(1.0));
		assertEquals("two", table.get(2.0));

		// > t = {[1]="im lost", "one", "two"}
		// > for k,v in pairs(t) do print(k,v) >> 1->"one", 2->"two"
		environment = new LocalEnvironment();
		visitor = new ExpVisitor(environment, null);

		fieldlist = new FieldList(new FieldLRExp(new NumberExp(2.0), new TextExp("im lost")));
		fieldlist.append(new FieldExp(new TextExp("two")));
		fieldlist.append(new FieldExp(new TextExp("one")));
		visitor.visit(tableconstructorexp);
		table = (LuaTable) visitor.popLast();
		assertEquals("one", table.get(1.0));
		assertEquals("two", table.get(2.0));

	}

	@Test
	public void testVisitBinop() {
		Exp exp = new Binop(new TextExp("a"), Op.CONCAT, new TextExp("b"));
		exp.accept(visitor);
		assertEquals("ab", visitor.popLast());
	}

	@Test
	public void testVisitUnop() {
		Exp exp = new Unop(Op.LEN, new TextExp("a"));
		exp.accept(visitor);
		assertEquals(1.0, visitor.popLast());
	}

	@Test
	public void testVisitPrefixExpVar() {
		environment.setLocal("abc", "123");
		Exp exp = new PreExp(new PrefixExpVar(new Variable("abc")));
		exp.accept(visitor);
		assertEquals("123", visitor.popLast());
	}

}
