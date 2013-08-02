package edu.tum.lua.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import edu.tum.lua.Preconditions;
import edu.tum.lua.types.LuaType;

public class PreconditionsTest {

	@Test
	public void test() {
		assertTrue(checkArguments(new LuaType[][] {}, new Object[] {}));

		LuaType[][] types = new LuaType[][] { null };
		assertFalse(checkArguments(types, new Object[] {}));
		assertTrue(checkArguments(types, new Object[] { new Double(0) }));
		assertTrue(checkArguments(types, new Object[] { new Boolean(false) }));
		assertTrue(checkArguments(types, new Object[] { "String", new Double(0) }));

		types = new LuaType[][] { { LuaType.NUMBER } };
		assertFalse(checkArguments(types, new Object[] {}));
		assertTrue(checkArguments(types, new Object[] { new Double(0) }));
		assertFalse(checkArguments(types, new Object[] { new Boolean(true) }));
		assertTrue(checkArguments(types, new Object[] { new Double(0), new Boolean(true) }));

		types = new LuaType[][] { { null, LuaType.NUMBER } };
		assertTrue(checkArguments(types, new Object[] {}));
		assertTrue(checkArguments(types, new Object[] { new Double(0) }));
		assertFalse(checkArguments(types, new Object[] { new Boolean(true) }));
		assertTrue(checkArguments(types, new Object[] { new Double(0), new Boolean(true) }));

		types = new LuaType[][] { { LuaType.NUMBER }, { LuaType.STRING } };
		assertTrue(checkArguments(types, new Object[] { new Double(0), "String" }));
		assertFalse(checkArguments(types, new Object[] { "String", new Double(0) }));
		assertFalse(checkArguments(types, new Object[] { new Double(0) }));

		types = new LuaType[][] { null, { null, LuaType.NUMBER } };
		assertTrue(checkArguments(types, new Object[] { "String" }));
		assertTrue(checkArguments(types, new Object[] { "String", new Double(0) }));
		assertTrue(checkArguments(types, new Object[] { new Boolean(true), new Double(0) }));
		assertTrue(checkArguments(types, new Object[] { new Boolean(false), new Double(0), new Boolean(true) }));
	}

	private boolean checkArguments(LuaType[][] types, Object[] arguments) {
		try {
			Preconditions.checkArguments("", Arrays.asList(arguments), types);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
