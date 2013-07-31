package edu.tum.lua.cli;

import java.io.IOException;
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
import edu.tum.lua.stdlib.Print;

public class CommandLine {

	private final static GlobalEnvironment environment = new GlobalEnvironment();
	private ConsoleReader reader;
	private SimpleCompletor completor;
	private History history;

	public CommandLine() throws IOException {
		reader = new ConsoleReader();
		reader.setBellEnabled(false);
		reader.setDefaultPrompt("> ");

		completor = new SimpleCompletor(new String[] {});
		completor.setCandidates(getStringSubset(environment.keySet()));
		reader.addCompletor(completor);

		history = new History();
	}

	public void run() throws IOException {
		StringBuilder chunk = new StringBuilder();
		Print printer = new Print();
		String line;

		while ((line = reader.readLine()) != null) {
			history.addToHistory(line);

			switch (line.split(" ")[0]) {
			case "--end":
			case "--exit":
				System.exit(0);
			}

			// allows e.g. "= 5" input in interactive mode
			if (line.startsWith("=")) {
				line = line.replaceFirst("=", "return ");
			}

			chunk.append(line);

			try {
				Block block = ParserUtil.loadStringInteractive(chunk.toString());
				printer.apply(LuaInterpreter.eval(block, environment));
				completor.setCandidates(getStringSubset(environment.keySet()));
				chunk = new StringBuilder();
				reader.setDefaultPrompt("> ");
			} catch (StatementNotFinishedException snfe) {
				reader.setDefaultPrompt(">> ");
				chunk.append(" ");
			} catch (SyntaxError se) {
				chunk = new StringBuilder();
				System.out.println("Syntax error while parsing");
				reader.setDefaultPrompt("> ");
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
}
