package edu.tum.lua.stdlib;

import static edu.tum.lua.Preconditions.checkArguments;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.tum.lua.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Next extends LuaFunctionNative {

	private Object index;
	private LuaTable table;
	private Iterator<Object> iter;

	private Object nextindex;
	private Object nextvalue;

	LuaType[][] expectedTypes = { { LuaType.TABLE } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		/*
		 * Usage: Next.apply(LuaTable table, [KeyableValue index]). Returns the
		 * next index and its associated value.
		 */

		checkArguments("next", arguments, expectedTypes);

		table = (LuaTable) arguments.get(0);

		// If the second argument is absent, then it is interpreted as nil.
		if (arguments.size() != 2) {
			index = null;
		} else {
			index = arguments.get(1);
		}

		// When called with nil as its second argument, next returns an initial
		// index and its associated value
		if (index == null) {

			// With nil in an empty table, next returns nil.
			if (!iter.hasNext()) {
				return null;
			}

			nextindex = iter.next();
			nextvalue = table.get(nextindex);

			return Arrays.asList(nextindex, nextvalue);
		}

		// Return next index and next associated value
		while (iter.hasNext()) {

			if (iter.next() == index) {
				// When called with the last index, next returns nil.
				if (!iter.hasNext()) {
					return null;
				}
				nextindex = iter.next();
				nextvalue = table.get(nextindex);
				return Arrays.asList(nextindex, nextvalue);
			}

		}

		// If index is not valid throw error
		throw new LuaRuntimeException("Invalid key for next");

	}
}
