package edu.tum.lua.cli;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import jline.ConsoleReader;
import jline.History;
import jline.SimpleCompletor;
import util.ParserUtil;
import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LuaInterpreter;
import edu.tum.lua.ast.Block;

public class Main {

	private static GlobalEnvironment environment = new GlobalEnvironment();
	private static Deque<Keyword> keysStack = new LinkedList<>();

	public static void main(String... args) throws Exception {

		ConsoleReader reader = new ConsoleReader();
		reader.setBellEnabled(false);
		reader.setDefaultPrompt("> ");

		SimpleCompletor completer = new SimpleCompletor(new String[] {});
		completer.setCandidates(getStringSubset(environment.keySet()));
		reader.addCompletor(completer);

		History history = new History();
		List<Object> results = null;
		StringBuilder result = new StringBuilder();

		String line;
		StringBuilder chunk = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			history.addToHistory(line);

			// allows "= 5" input in interactive mode
			if (line.startsWith("=")) {
				line = line.replaceFirst("=", "return ");
			}

			manageKeys(line);
			chunk.append(line);

			if (keysStack.isEmpty()) {

				reader.setDefaultPrompt("> ");
				Block block = ParserUtil.loadString(chunk.toString());
				results = LuaInterpreter.eval(block, environment);

				completer.setCandidates(getStringSubset(environment.keySet()));
				chunk = new StringBuilder();

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

				result = new StringBuilder();
			} else if (keysStack.peek() == Keyword.QUOTATION) {
				keysStack.pop();
			} else {
				reader.setDefaultPrompt(">> ");
			}

		}
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

	// FIXME: support all multi line inputs?
	/**
	 * reads whole strings or puts expected keywords onto stack
	 * 
	 * @param line
	 */
	private static void manageKeys(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			StringBuilder intermediateResult = new StringBuilder();

			// FIXME: resolve '"test""bla"'
			try {
				intermediateResult.append(token);

				if (token.startsWith("\"")) {
					if (token.contains("\"")) {
						break;
					}
					do {
						token = tokenizer.nextToken();
						intermediateResult.append(token);
					} while (token.endsWith("\""));
				}

				if (token.startsWith("'")) {
					if (token.contains("'")) {
						break;
					}
					do {
						token = tokenizer.nextToken();
						intermediateResult.append(token);
					} while (token.endsWith("'"));
				}
			} catch (NoSuchElementException nsee) {
				keysStack.push(Keyword.QUOTATION);
				System.out.println("unfinished string near '" + intermediateResult + "'");
				break;
			}

			switch (token) {
			case "function":
				keysStack.push(Keyword.END);
				break;
			case "do":
				if (keysStack.peek() == Keyword.DO) {
					keysStack.pop();
				}
				keysStack.push(Keyword.END);
				break;
			case "while":
				keysStack.push(Keyword.DO);
				break;
			case "for":
				keysStack.push(Keyword.DO);
				break;
			case "repeat":
				keysStack.push(Keyword.UNTIL);
				break;
			case "end":
				if (keysStack.peek() == Keyword.END) {
					keysStack.pop();
				}
			case "until":
				if (keysStack.peek() == Keyword.UNTIL) {
					keysStack.pop();
				}
			default:
				break;
			}
		}
	}
}
