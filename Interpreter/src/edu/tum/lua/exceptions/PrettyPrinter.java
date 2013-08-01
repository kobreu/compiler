package edu.tum.lua.exceptions;

import java.util.List;

public class PrettyPrinter {

	static public void print(LuaRuntimeException e) {

		System.out.println("Exception!");

		String errormessage = e.getMessage();

		if (e.getLocation() != null) {
			int errorcolumn = e.getLocation().getColumn();
			System.out.println("Location:" + e.getLocation().getRow() + " " + e.getLocation().getColumn());
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
		// TODO change int to String
		int filename = /* stacktrace.location.getFileName() */0;
		int row = -1;
		if (stacktrace.location != null) {
			row = stacktrace.location.getRow();
		}
		String functionname = stacktrace.functionName;
		List<Object> args = stacktrace.args;

		System.out.printf("%d:%d: %s(", filename, row, functionname);

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
