package edu.tum.lua.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.ast.FunctionNode;
import edu.tum.lua.types.LuaFunction;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class LuaTableTest {

	private LuaTable table;
	private LuaTable table_unset;
	
	@Before
	public void setUp() throws Exception {
		table = new LuaTable();
		table_unset = new LuaTable();
	}

	@Test
	public void testGet() {
		
		table = new LuaTable();
		
		// Nil
		assertEquals(null, table.get(null));
		
		// Boolean
		table.set(true, "value1");
		table.set(false, "value2");
		assertEquals("value1", table.get(true));
		assertEquals("value2", table.get(false));
		assertEquals(null, table_unset.get(true));
		assertEquals(null, table_unset.get(false));
		
		// Number
		table.set(1.0, "value3");
		assertEquals("value3", table.get(1.0));
		assertEquals(null, table_unset.get(1.0));
		
		// String
		table.set("string", "value4");
		assertEquals("value4", table.get("string"));
		assertEquals(null, table_unset.get("string"));
		
		// Function as index
		LuaTable env = new LuaTable();
		FunctionNode funnode = new FunctionNode();
		LuaFunction f1 = new LuaFunction(env, funnode);
		table.set(f1, "value5");
		assertEquals("value5", table.get(f1));
		assertEquals(null, table_unset.get(f1));
		
		// Table as index
		LuaTable table2 = new LuaTable();
		table.set(table2, "value6");
		assertEquals("value6", table.get(table2));
		assertEquals(null, table_unset.get(table2));
		
		/*
		 *  Forward Table search
		 */
		
		// Nil
		
		// Booleans
		table = new LuaTable();
		LuaTable fwdtable = new LuaTable();
		fwdtable.set(true, "value_forward");
		table.setIndex(fwdtable);
		assertEquals("value_forward", table.get(true));
		table.set(true, "value_normal");
		assertEquals("value_normal", table.get(true));
		
		/* 
		 * Forward Method search
		 */
		
		// Booleans
		table = new LuaTable();

		LuaTable fTable = new LuaTable();
		FunctionNode fNode = new FunctionNode();
		LuaFunction f2 = new LuaFunction(fTable, fNode);
		table.setIndex(f2);
		
		// TODO
		assertEquals(f2.apply(true), table.get(true) );
		
	}
	

	@Test
	public void testGetLuaTable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetString() {
		
		table = new LuaTable();
		
		// Defined String
		table.set("definedstring", "value1");
		assertEquals("value1", table.get("definedstring"));
		
		// Undefined String should be nil
		assertEquals(null, table.get("undefinedstring"));
	
	}

	@Test
	public void testGetNumber() {
		
		table = new LuaTable();
		
		// Defined Number
		table.set(6.0, "value1");
		assertEquals("value1", table.get(6.0));
		
		// Undefined Number should be nil
		assertEquals(null, table.get(10.0));
		
	}

	@Test
	public void testGetLuaFunction() {
		
		table = new LuaTable();
		
		// Defined Function
		LuaTable env = new LuaTable();
		FunctionNode funnode = new FunctionNode();
		LuaFunction f = new LuaFunction(env, funnode);
		table.set(f, "value1");
		assertEquals("value1", table.get(f));
		
		// Undefined Function should be nil
		LuaFunction g = new LuaFunction(env, funnode);
		assertEquals(null, table.get(g));
		
	}

	@Test
	public void testGetBoolean() {
		
		table = new LuaTable();
		
		table.set(false, "value1");
		assertEquals("value1", table.get(false));
		table.set(true, "value2");
		assertEquals("value2", table.get(true));
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetNil() {
		table.get(null);
	}
	
	public void testSet() {
		
		// TODO
		
	}

	public void testSetIndex() {

		// TODO
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNil() {
		// Test Indexes 
		table.set(null, "string_value");
	}

	@Test
	public void testUnset() {
		fail("Not yet implemented"); // TODO
	}

}
