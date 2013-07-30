package edu.tum.cli;

import java.io.IOException;

import jline.ConsoleReader;

public class Main {

	public static void main(String... args) throws IOException {

		while (true) {
			ConsoleReader reader = new ConsoleReader();

			System.out.println(reader.readLine());
		}
	}
}
