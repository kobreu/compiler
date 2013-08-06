package edu.tum.juna.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jline.ConsoleReader;
import jline.History;
import jline.SimpleCompletor;
import edu.tum.juna.GlobalEnvironment;
import edu.tum.juna.LuaInterpreter;
import edu.tum.juna.ast.Block;
import edu.tum.juna.exceptions.LuaRuntimeException;
import edu.tum.juna.exceptions.PrettyPrinter;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.parser.exception.StatementNotFinishedException;
import edu.tum.juna.parser.exception.SyntaxError;
import edu.tum.juna.stdlib.DoFile;
import edu.tum.juna.stdlib.Print;

public class CommandLine {

	private final GlobalEnvironment environment;
	private final ConsoleReader reader;
	private final SimpleCompletor completor;
	private final History history;
	private final Print printer;
	private Documentation doc = null;
	private final PrettyPrinter prettyPrinter;

	public CommandLine() throws Exception {
		environment = new GlobalEnvironment();

		reader = new ConsoleReader();
		reader.setBellEnabled(false);
		reader.setDefaultPrompt("> ");

		completor = new SimpleCompletor(new String[] {});
		completor.setCandidates(getStringSubset(environment.keySet()));
		reader.addCompletor(completor);

		history = new History();

		printer = new Print();

		prettyPrinter = new PrettyPrinter();
	}

	public void doFile(String file) {
		new DoFile(environment).apply(file);
	}

	public void run() throws IOException {
		StringBuilder chunk = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			history.addToHistory(line);

			if (line.startsWith("--")) {
				if (doc == null) {
					try {
						doc = new Documentation(reader.getTermwidth());
					} catch (Exception e) {
						System.out.println("no ducumentation there");
						continue;
					}
				}

				switch (line.split(" ")[0]) {
				case "--end":
				case "--exit":
				case "--e":
					System.exit(0);
				case "--help":
				case "--h":
				case "--?":
					doc.printHelp();
					continue;
				case "--list":
				case "--l":
					if (line.split(" ").length > 1) {
						doc.listSpecialFunction(line.split(" ")[1]);
					} else {
						doc.listFunctions(environment);
					}
					continue;
				case "--env":
					doc.listEnvironment(environment);
					continue;
				}
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
				printer.apply(Arrays.asList("Syntax error while parsing"));
				reader.setDefaultPrompt("> ");
			} catch (LuaRuntimeException e) {
				prettyPrinter.print(e);
				chunk = new StringBuilder();
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
