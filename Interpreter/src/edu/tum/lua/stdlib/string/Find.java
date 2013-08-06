package edu.tum.lua.stdlib.string;

import java.util.Arrays;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import edu.tum.lua.Preconditions;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class Find extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.STRING, LuaType.NUMBER }, { LuaType.STRING, LuaType.NUMBER },
			{ null, LuaType.NUMBER }, { null, LuaType.BOOLEAN } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("string.find", arguments, expectedTypes);

		if (LuaType.getTypeOf(arguments.get(0)) == LuaType.NUMBER) {
			arguments.set(0, ToString.toString(arguments.get(0)));
			return apply(arguments);
		}

		if (LuaType.getTypeOf(arguments.get(1)) == LuaType.NUMBER) {
			arguments.set(1, ToString.toString(arguments.get(1)));
			return apply(arguments);
		}

		if (arguments.size() == 2) {
			return apply(arguments.get(0), arguments.get(1), new Double(1));
		}

		if (arguments.size() == 3) {
			return apply(arguments.get(0), arguments.get(1), arguments.get(2), new Boolean(false));
		}

		int offset = (int) Math.rint(((Double) arguments.get(2)).doubleValue());
		if (offset != 0) {
			arguments.set(0, ((String) arguments.get(0)).substring(offset - 1));
			arguments.set(2, new Double(0));
			List<Object> result = apply(arguments);

			Double adjustment = new Double(offset);
			if (result.size() == 2) {
				result.set(0, ((Double) result.get(0)) + adjustment);
				result.set(1, ((Double) result.get(1)) + adjustment);
			}
			return result;
		}

		String s = (String) arguments.get(0);
		String p = (String) arguments.get(1);

		if ((Boolean) arguments.get(3) == true) {
			int i = s.indexOf(p);
			if (i == -1) {
				return Arrays.asList(new Object[] { null });
			} else {
				return Arrays.asList(new Object[] { new Double(i), new Double(i + p.length() - 1) });
			}
		}

		int[] loc = getIndexOfPattern(s, p);
		if (loc[0] == -1) {
			return Arrays.asList(new Object[] { null });
		} else {
			return Arrays.asList(new Object[] { new Double(loc[0]), new Double(loc[1]) });
		}
	}

	public static int[] getIndexOfPattern(String s, String p) {
		p = convertPattern(p);

		String sub;

		int i = 0, j = 0;

		BEGIN: {
			for (i = 0; i < s.length(); i++) {
				for (j = i + 1; j <= s.length(); j++) {
					sub = s.substring(i, j);
					if (sub.matches(p)) {
						break BEGIN;
					}
				}
			}
		}

		if (i == s.length() && i + 1 == j) {
			return new int[] { -1, -1 };
		}

		END: {
			while (j < s.length()) {
				sub = s.substring(i, j + 1);
				if (sub.matches(p)) {
					j++;
				} else {
					break END;
				}
			}
		}

		// until here begin and end index is in Java indexing style: {x,x+1} for
		// a char at position x; Lua index the x-th char as {x,x}

		return new int[] { i, j - 1 };
	}

	public static String convertPattern(String s) {
		String p = s;

		if (p.length() == 0) {
			return "[^.]*";
		}

		p = p.replaceAll("\\{", "\\\\{");
		p = p.replaceAll("\\}", "\\\\}");

		if (p.matches(".*%z.*")) {
			throw new NotImplementedException();
		}

		if (p.matches(".*%b...*")) {
			throw new NotImplementedException();
		}

		if (p.matches(".*%[1-9].*")) {
			throw new NotImplementedException();
		}

		p = p.replaceAll("%A", "[^%a]");
		p = p.replaceAll("%C", "[^%c]");
		p = p.replaceAll("%D", "[^%d]");
		p = p.replaceAll("%L", "[^%l]");
		p = p.replaceAll("%P", "[^%p]");
		p = p.replaceAll("%S", "[^%s]");
		p = p.replaceAll("%U", "[^%u]");
		p = p.replaceAll("%W", "[^%w]");
		p = p.replaceAll("%X", "[^%x]");

		p = p.replaceAll("%a", "[a-zA-Z]");
		p = p.replaceAll("%c", "\\\\p{Cntrl}");
		p = p.replaceAll("%d", "\\\\d");
		p = p.replaceAll("%l", "\\\\p{Lower}");
		p = p.replaceAll("%p", "\\\\p{Punct}");
		p = p.replaceAll("%s", "\\\\p{Space}");
		p = p.replaceAll("%u", "\\\\p{Upper}");
		p = p.replaceAll("%w", "\\\\p{Alnum}");
		p = p.replaceAll("%x", "\\\\p{XDigit}");

		if (p.matches(".*\\]-.*")) {
			throw new NotImplementedException();
		}

		// replace escaped sequences
		p = p.replaceAll("%%", "%");
		p = p.replaceAll("%\\+", "\\\\+");
		p = p.replaceAll("%\\?", "\\\\?");
		p = p.replaceAll("%\\*", "\\\\*");
		p = p.replaceAll("%\\-", "\\\\-");
		p = p.replaceAll("%\\^", "\\\\^");
		p = p.replaceAll("%\\$", "\\\\$");
		p = p.replaceAll("%\\(", "\\\\(");
		p = p.replaceAll("%\\)", "\\\\)");
		p = p.replaceAll("%\\[", "\\\\[");
		p = p.replaceAll("%\\]", "\\\\]");

		return p;
	}
}
