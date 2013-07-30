package edu.tum.lua.ast;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public final class LegacyAdapter {

	public static List<Stat> convert(StatList statList) {
		if (statList == null) {
			return Collections.emptyList();
		}

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
