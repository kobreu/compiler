package edu.tum.cli;

import java.io.IOException;

import jline.ConsoleReader;
import jline.SimpleCompletor;

public class Main {

	public static void main(String... args) throws IOException {
		// TODO Create Environment here

		ConsoleReader reader = new ConsoleReader();
		reader.setBellEnabled(false);
		reader.setDefaultPrompt("> ");
		reader.addCompletor(new SimpleCompletor(new String[] { "replacements" }));
		// TODO add environment keys above

		String line;
		while ((line = reader.readLine()) != null) {
			// keyword stack: count open blocks

			System.out.println(line);

			// execute Line with environment
			// optional print non empty return lists

			// update completors
		}
	}
}
