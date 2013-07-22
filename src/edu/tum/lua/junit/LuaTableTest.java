package edu.tum.lua.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class LuaTableTest {

	private LuaTable table;
	
	@Before
	public void setUp() throws Exception {
		table = new LuaTable();
	}

	@Test
	public void testGetType() {
		table.set("key1", true);
		assertEquals(LuaType.BOOLEAN, table.getType("key1"));
		assertEquals(LuaType.NIL, table.getType("key2"));		
	}

	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetLuaTable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetLuaFunction() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetObjectLuaTable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetObjectString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetObjectDouble() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetObjectLuaFunction() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetObjectBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUnset() {
		fail("Not yet implemented"); // TODO
	}

}
