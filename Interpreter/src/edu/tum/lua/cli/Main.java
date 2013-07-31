package edu.tum.lua.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jline.ConsoleReader;
import jline.History;
import jline.SimpleCompletor;
import util.ParserUtil;
import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.exception.StatementNotFinishedException;
import edu.tum.lua.parser.exception.SyntaxError;

public class Main {

	private static GlobalEnvironment environment = new GlobalEnvironment();

	public static void main(String... args) throws IOException {

		ConsoleReader reader = new ConsoleReader();
		reader.setBellEnabled(false);
		reader.setDefaultPrompt("> ");

		SimpleCompletor completer = new SimpleCompletor(new String[] {});
		completer.setCandidates(getStringSubset(environment.keySet()));
		reader.addCompletor(completer);

		History history = new History();
		List<Object> results = null;

		String line;
		StringBuilder chunk = new StringBuilder();

		while ((line = reader.readLine()) != null) {
			history.addToHistory(line);

			reader.setDefaultPrompt("> ");

			if (line.startsWith("dofile ")) { // load lua files
				String todo = line;
				line = dofile(todo);

			}

			// allows e.g. "= 5" input in interactive mode
			if (line.startsWith("=")) {
				line = line.replaceFirst("=", "return ");
			}

			chunk.append(line);

			try {
				Block block = ParserUtil.loadStringInteractive(chunk.toString());
				results = LuaInterpreter.eval(block, environment);

				completer.setCandidates(getStringSubset(environment.keySet()));
				chunk = new StringBuilder();

				printResult(results);
			} catch (StatementNotFinishedException snfe) {
				reader.setDefaultPrompt(">> ");
				chunk.append(" ");
			} catch (SyntaxError se) {
				chunk = new StringBuilder();
				System.out.println("Syntax error while parsing");
			}

		}

	}

	private static String dofile(String line) {
		line = line.substring(6);
		Block block;
		try {
			block = ParserUtil.loadFile(line);
			printResult(LuaInterpreter.eval(block, environment));
		} catch (FileNotFoundException e) {
			System.out.println("cannot open " + line + ": No such file or directory");
		} catch (SyntaxError se) {
			System.out.println("Syntax error");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error while parsing file");
			e.printStackTrace();
		}
		return line;
	}

	private static SortedSet<String> getStringSubset(Set<Object> keySet) {
		SortedSet<String> subset = new TreeSet<>();

		for (Object o : keySet) {
			if (o instanceof String) {
				subset.add((String) o);
			}
		}

		return subset;
	}

	private static void printResult(List<Object> results) {
		StringBuilder result = new StringBuilder();
		if (results != null && !results.isEmpty()) {
			for (Object r : results) {
				if (r instanceof String) {
					result.append((String) r);
				} else {
					result.append(r);
				}
				result.append(", ");
			}
			result.setLength(result.length() - 2);
			System.out.println(result);
		}
		results = null;
	}
}
