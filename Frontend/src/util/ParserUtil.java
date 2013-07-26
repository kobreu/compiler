package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;

public class ParserUtil {

	public static Block loadString(String block) throws Exception {
		return parse(new StringReader(block));
	}

	public static Block loadFile(String uri) throws FileNotFoundException, Exception {
		return loadFile(new File(uri));
	}

	public static Block loadFile(File file) throws FileNotFoundException, Exception {
		try (FileReader reader = new FileReader(file)) {
			return parse(reader);
		}
	}

	private static Block parse(Reader reader) throws Exception {
		Lexer scanner = new Lexer(reader);

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
