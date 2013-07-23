package edu.tum.lua.ast;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public final class LegacyAdapter {

	@SuppressWarnings("unchecked")
	public static List<Stat> convert(StatList statList) {
		return convert(statList.elements());
	}

	@SuppressWarnings("unchecked")
	public static List<String> convert(NameList nameList) {
		return convert(nameList.elements());
	}

	public static <T> List<T> convert(Enumeration<T> iterator) {
		List<T> list = new LinkedList<>();

		while (iterator.hasMoreElements()) {
			list.add(iterator.nextElement());
		}

		return list;
	}
}
