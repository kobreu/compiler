package edu.tum.lua.exceptions;

public class PrettyPrinter {

	static public void print(LuaRuntimeException e) {

		System.out.println("Exception!");
		System.out.println("stack traceback:");

		for (LuaStackTraceElement stackTraceElement : e.stacktrace) {

			System.out.println("Stack detrace element");
			stackTraceElement.location();

		}

	}
}
