package edu.tum.lua.exceptions;

import java.util.List;

public class PrettyPrinter {

	static public void print(LuaRuntimeException e) {

		System.out.println("Exception!");

		String errormessage = e.getMessage();

		if (e.getLocation() != null) {
			int errorcolumn = e.getLocation().getColumn();
			printErrorWithArrow(errormessage, errorcolumn);
		} else {
			printError(errormessage);
		}

		if (e.stacktrace.size() != 0) {
			System.out.println("stack traceback:");
			for (LuaStackTraceElement stackTraceElement : e.stacktrace) {
				printStackTraceElement(stackTraceElement);
			}
		}

	}

	static private void printError(String errormessage) {
		System.out.println(errormessage);
	}

	static private void printErrorWithArrow(String errormessage, int col) {
		/*
		 * Prints out the error message with an arrow pointing to column
		 */

		printError(errormessage);

		for (int i = 0; i < col - 1; ++i) {
			System.out.print("-");
		}
		System.out.println("^");

	}

	static private void printStackTraceElement(LuaStackTraceElement stacktrace) {
		/*-
		 * Print something like this:
		 * main.lua:5 print("error")
		 */

		// TODO remove this output
		System.out.println("Stack detrace element");

		// TODO change int to String
		int filename = stacktrace.location.getFileName();
		int row = stacktrace.location.getRow();
		String functionname = stacktrace.functionName;
		List<Object> args = stacktrace.args;

		System.out.printf("%i:%i: %s(", filename, row, functionname);

		boolean afterFirst = false;
		for (Object arg : args) {
			if (afterFirst) {
				System.out.print(", ");
			}

			System.out.print(arg.toString());
			afterFirst = true;
		}
		System.out.println(")");
	}
}
