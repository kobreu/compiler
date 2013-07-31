package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;
import edu.tum.lua.parser.exception.StatementNotFinishedException;
import edu.tum.lua.parser.exception.SyntaxError;

public class ParserUtil {

	public static Block loadString(String block) throws Exception {
		return parse(new StringReader(block));
	}
	
	public static Block loadStringInteractive(String block) throws StatementNotFinishedException, SyntaxError {
		Lexer scanner = new Lexer(new StringReader(block));
		Block prog = null;

		Parser p = new Parser(scanner);
		try {
			prog = (Block) p.parse().value;
		} catch(Exception ex) { // syntax exception or statement not finished?
			if (p.line == -1 && p.column == -1) {
				throw new StatementNotFinishedException();
			} else {
				throw new SyntaxError();
			}
		}
		
		return prog;
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
