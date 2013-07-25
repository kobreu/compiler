package edu.tum.lua.ast;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public final class LegacyAdapter {

	public static List<Stat> convert(StatList statList) {
		Enumeration<Stat> Enum = statList.elements();
		return convert(Enum);
	}

	public static List<String> convert(NameList nameList) {
		Enumeration<String> Enum = nameList.elements();
		return convert(Enum);
	}

	public static List<String> convert(VarList varlist) {
		Enumeration<Var> iterator = varlist.elements();
		List<String> list = new LinkedList<>();

		if (iterator.hasMoreElements()) {
			Var var = iterator.nextElement();

			if (var instanceof Variable) {
				list.add(((Variable) var).var);
			} else {
				throw new RuntimeException("Not yet implemented");
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Exp> convert(ExpList explist) {
		return convert(explist.elements());
	}

	public static <T> List<T> convert(Enumeration<T> iterator) {
		List<T> list = new LinkedList<>();

		while (iterator.hasMoreElements()) {
			list.add(iterator.nextElement());
		}

		return list;
	}
}
