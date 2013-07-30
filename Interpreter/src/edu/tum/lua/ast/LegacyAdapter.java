package edu.tum.lua.ast;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public final class LegacyAdapter {

<<<<<<< HEAD
	@SuppressWarnings("unchecked")
	public static List<Stat> convert(StatList statList) {
		return convert(statList.elements());
	}

	@SuppressWarnings("unchecked")
	public static List<String> convert(NameList nameList) {
		return convert(nameList.elements());
=======
	public static List<Stat> convert(StatList statList) {
		Enumeration<Stat> Enum = statList.elements();
		return convert(Enum);
	}

	public static List<String> convert(NameList nameList) {
		Enumeration<Name> iterator = nameList.elements();
		List<String> list = new LinkedList<>();

		if (iterator.hasMoreElements()) {
			list.add(iterator.nextElement().name);
		}

		return list;
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
>>>>>>> Parser
	}

	public static <T> List<T> convert(Enumeration<T> iterator) {
		List<T> list = new LinkedList<>();

		while (iterator.hasMoreElements()) {
			list.add(iterator.nextElement());
		}

		return list;
	}
}
