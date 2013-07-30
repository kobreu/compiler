package edu.tum.lua.cli;

import java.util.Deque;
import java.util.LinkedList;
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

		String line;
		StringBuilder chunk = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			history.addToHistory(line);
			manageKeys(line);
			chunk.append(line);

			if (keysStack.isEmpty()) {
				Block block = ParserUtil.loadString(chunk.toString());
				LuaInterpreter.eval(block, environment);
				completer.setCandidates(getStringSubset(environment.keySet()));
				chunk = new StringBuilder();
			}

			// TODO:
			// optional print non empty return lists
			// print directly after '=' or 'return'
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
	private static void manageKeys(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			try {
				if (token.startsWith("\"")) {
					do {
						token = tokenizer.nextToken();
					} while (token.endsWith("\""));
				}

				if (token.startsWith("'")) {
					do {
						token = tokenizer.nextToken();
					} while (token.endsWith("'"));
				}
			} catch (NoSuchElementException nsee) {
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
