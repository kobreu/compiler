package edu.tum.juna.junit.operator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.operator.logical.AndOperator;
import edu.tum.juna.operator.logical.NotOperator;
import edu.tum.juna.operator.logical.OrOperator;
import edu.tum.juna.stdlib.VoidFunction;
import edu.tum.juna.types.LuaTable;

public class LogicalOperatorTest {

	private final Object[] falseObjects = { null, false };
	private final Object[] trueObjects = { "a", new LuaTable(), new VoidFunction(), 1.0, true };

	@Test
	public void testSpecial() throws Exception {
		GlobalEnvironment _G = new GlobalEnvironment();
		Block block = edu.tum.juna.parser.ParserUtil.loadString("function f (x) return x and x.y or 0 end");
		LuaInterpreter.eval(block, _G);
	}

	@Test
	public void testAnd() {
		AndOperator and = new AndOperator();

		for (Object trueObject1 : trueObjects) {
			for (Object trueObject2 : trueObjects) {
				assertEquals(trueObject2, and.apply(trueObject1, trueObject2));
			}

			for (Object falseObject : falseObjects) {
				assertEquals(falseObject, and.apply(trueObject1, falseObject));
			}
		}

		for (Object falseObject1 : falseObjects) {
			for (Object trueObject : trueObjects) {
				assertEquals(falseObject1, and.apply(falseObject1, trueObject));
			}

			for (Object falseObject2 : falseObjects) {
				assertEquals(falseObject1, and.apply(falseObject1, falseObject2));
			}
		}
	}

	@Test
	public void testNot() {
		NotOperator not = new NotOperator();

		for (Object trueObject : trueObjects) {
			assertEquals(false, not.apply(trueObject));
		}

		for (Object falseObject : falseObjects) {
			assertEquals(true, not.apply(falseObject));
		}
	}

	@Test
	public void testOr() {
		OrOperator or = new OrOperator();

		for (Object trueObject1 : trueObjects) {
			for (Object trueObject2 : trueObjects) {
				assertEquals(trueObject1, or.apply(trueObject1, trueObject2));
			}

			for (Object falseObject : falseObjects) {
				assertEquals(trueObject1, or.apply(trueObject1, falseObject));
			}
		}

		for (Object falseObject1 : falseObjects) {
			for (Object trueObject : trueObjects) {
				assertEquals(trueObject, or.apply(falseObject1, trueObject));
			}

			for (Object falseObject2 : falseObjects) {
				assertEquals(falseObject2, or.apply(falseObject1, falseObject2));
			}
		}
	}
}
