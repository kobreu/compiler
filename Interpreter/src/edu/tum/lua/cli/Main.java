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

	private final static GlobalEnvironment environment = new GlobalEnvironment();
	private static ConsoleReader reader;
	private static SimpleCompletor completor;
	private static History history;

	public static void main(String... args) throws IOException {
		reader = new ConsoleReader();
		reader.setBellEnabled(false);
		reader.setDefaultPrompt("> ");

		if (args.length > 0) {
			switch (args[0]) {
			case "-h":
			case "-?":
			case "-help":
				System.out.println("Not implemented yet");
				return;
			case "-i":
				if (args.length >= 2) {
					dofile(args[1]);
				}
				break;
			case "-f":
				if (args.length < 2) {
					System.out.println("with -f you need to specify a second argument. See -h for help");
					return;
				}
				dofile(args[1]);
				return;
			default:
				dofile(args[0]);
				return;
			}
		}

		completor = new SimpleCompletor(new String[] {});
		completor.setCandidates(getStringSubset(environment.keySet()));
		reader.addCompletor(completor);

		history = new History();

		StringBuilder chunk = new StringBuilder();
		String line;

		LOOP: while ((line = reader.readLine()) != null) {
			history.addToHistory(line);
			reader.setDefaultPrompt("> ");

			switch (line.split(" ")[0]) {
			case "dofile":
				dofile(line.split("\"")[1]);
				line = line.replaceAll("^(dofile )\".*\" ", "");
				return;
			case "--end":
			case "--exit":
				break LOOP;
			default:
				break;
			}

			// allows e.g. "= 5" input in interactive mode
			if (line.startsWith("=")) {
				line = line.replaceFirst("=", "return ");
			}

			chunk.append(line);

			interprete(chunk);
		}
		System.out.println("end");
	}

	private static void interprete(StringBuilder chunk) {
		List<Object> results;
			try {
				Block block = ParserUtil.loadStringInteractive(chunk.toString());
				results = LuaInterpreter.eval(block, environment);

			completor.setCandidates(getStringSubset(environment.keySet()));
			chunk.setLength(0);

				printResult(results);
			} catch (StatementNotFinishedException snfe) {
				reader.setDefaultPrompt(">> ");
				chunk.append(" ");
			} catch (SyntaxError se) {
				chunk = new StringBuilder();
				System.out.println("Syntax error while parsing");
			}
		}

	private static String dofile(String line) {
		line = line.substring(6);
		Block block;
	private static void dofile(String file) {
		try {
			Block block = ParserUtil.loadFile(file);
			printResult(LuaInterpreter.eval(block, environment));
		} catch (FileNotFoundException e) {
			System.out.println("cannot open " + file + ": No such file or directory");
		} catch (SyntaxError se) {
			System.out.println("Syntax error");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error while parsing file");
			e.printStackTrace();
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
