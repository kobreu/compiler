package edu.tum.lua.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.operator.list.ConcatOperator;
import edu.tum.lua.types.LuaTable;

public class ConcatOperatorTest {

	
	ConcatOperator op;
	
	@Before
	public void setUp() throws Exception{
		op = new ConcatOperator();
	}
	
	@Test
	public void testApply() {
		ConcatOperator op = new ConcatOperator();
		String op1 = "abc";
		String op2 = "def";
		double op3 = 3;
		double op4 = 4;
		
		try{
			assertEquals(op1.concat(op2), op.apply(op1, op2));
			assertEquals(op1.concat(((Object) op3).toString()), op.apply(op1, op3));
			assertEquals(((Object) op3).toString().concat(op2), op.apply(op3, op2));
			assertEquals(((Object) op3).toString().concat(((Object) op4).toString()), op.apply(op3, op4));
		}
		catch (NoSuchMethodException e){
			fail();
		}
		
		LuaTable table = new LuaTable();
		table.setMetatable(new LuaTable());

		Object[] invalidObjects = { null, false, true, new LuaTable(), table };

		for (Object inOp1 : invalidObjects) {
			for (Object inOp2 : invalidObjects) {
				try {
					op.apply(inOp1, inOp2);
				} catch (NoSuchMethodException ex) {
					continue;
				}

				fail("Missing NoSuchMethodException exception");
			}
		}
	}

}
