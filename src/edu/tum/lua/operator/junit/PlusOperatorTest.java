package edu.tum.lua.operator.junit;

import static org.junit.Assert.*;

import javax.naming.OperationNotSupportedException;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.operator.arithmetic.PlusOperator;

public class PlusOperatorTest {

	PlusOperator op;
	
	@Before
	public void setUp() throws Exception {

		op = new PlusOperator();
			
	}

	@Test
	public void testApply() throws NoSuchMethodException {
		// Number + Number
		assertEquals(10.0, op.apply(5.0, 5.0));
		
		// Number + String
		assertEquals(10.0, op.apply(5.0, "5.0"));
		
		// String + Number
		assertEquals(10.0, op.apply("5.0", 5.0));
		
		// String + String
		assertEquals(10.0, op.apply("5.0", "5.0"));
	}
	
	@Test(expected=OperationNotSupportedException.class)
	public void testApplyBoolean() {
		op.apply(false,false);
	}
	
}
