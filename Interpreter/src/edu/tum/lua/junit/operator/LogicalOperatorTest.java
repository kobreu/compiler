package edu.tum.lua.junit.operator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.tum.lua.operator.logical.AndOperator;
import edu.tum.lua.operator.logical.NotOperator;
import edu.tum.lua.operator.logical.OrOperator;
<<<<<<< HEAD:Interpreter/src/edu/tum/lua/junit/LogicalOperatorTest.java
import edu.tum.lua.stdlib.ExampleStdlibFunction;
=======
import edu.tum.lua.stdlib.VoidFunction;
>>>>>>> Parser:Interpreter/src/edu/tum/lua/junit/operator/LogicalOperatorTest.java
import edu.tum.lua.types.LuaTable;

public class LogicalOperatorTest {

	private final Object[] falseObjects = { null, false };
<<<<<<< HEAD:Interpreter/src/edu/tum/lua/junit/LogicalOperatorTest.java
	private final Object[] trueObjects = { "a", new LuaTable(), new ExampleStdlibFunction(), 1.0, true };
=======
	private final Object[] trueObjects = { "a", new LuaTable(), new VoidFunction(), 1.0, true };
>>>>>>> Parser:Interpreter/src/edu/tum/lua/junit/operator/LogicalOperatorTest.java

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
