package util;

import java.io.File;

import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;

public class ParserUtil {

	public static Block parse(String file) throws Exception {
		return parse(new File(file));
	}

	private static Block parse(File file) throws Exception {
		Lexer scanner = null;
		try {
			scanner = new Lexer(new java.io.FileReader(file));
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + file + "\"");
			System.exit(1);
		}

		Block prog = null;

		try {
			Parser p = new Parser(scanner);
			prog = (Block) p.parse().value;

		} catch (java.io.IOException e) {
			System.out.println("An I/O error occured while parsing : \n" + e);
			System.exit(1);
		}

		return prog;
	}

}
