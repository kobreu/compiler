package edu.tum.lua.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.Environment;
import edu.tum.lua.ExpVisitor;
import edu.tum.lua.ast.FieldExp;
import edu.tum.lua.ast.FieldLRExp;
import edu.tum.lua.ast.FieldList;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.TableConstructor;
import edu.tum.lua.ast.TableConstructorExp;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.types.LuaTable;

public class ExpVisitorTest {

	ExpVisitor visitor;
	Environment e;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testVisitNil() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitBooleanExp() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitNumberExp() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitTextExp() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitDots() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitClosure() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitTableConstructorExp() {

		// > t = {"one", "two", [3.0] = "three", x4 = "four", "5" = "five"}
		// > for k,v in pairs(t) do print(k,v) >> 1->"one", 2->"two"
		e = new Environment();
		visitor = new ExpVisitor(e);

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
		e = new Environment();
		visitor = new ExpVisitor(e);

		fieldlist = new FieldList(new FieldExp(new TextExp("one")));
		fieldlist.append(new FieldExp(new TextExp("two")));
		fieldlist.append(new FieldLRExp(new NumberExp(1.0), new TextExp("im lost")));
		visitor.visit(tableconstructorexp);
		table = (LuaTable) visitor.popLast();
		assertEquals("one", table.get(1.0));
		assertEquals("two", table.get(2.0));

		// > t = {[1]="im lost", "one", "two"}
		// > for k,v in pairs(t) do print(k,v) >> 1->"one", 2->"two"
		e = new Environment();
		visitor = new ExpVisitor(e);

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
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitUnop() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitPrefixExp() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisitPrefixExpVar() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVisit() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testExpVisitor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetReturn() {
		fail("Not yet implemented"); // TODO
	}

}
