package unittest.parser;

import static org.junit.Assert.*;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;

import serialization.XMLSerializer;
import edu.tum.lua.ast.Chunk;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;

public class TestParser extends XMLTestCase {

	@Test
	public void test() throws Exception {
		Lexer scanner = null;
		try {
			scanner = new Lexer(new java.io.FileReader("testinput/grammar/chunk_sequence_with_semicolons.lua"));
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java Main <inputfile>");
			System.exit(1);
		}

		Chunk prog = null;

		try {
			Parser p = new Parser(scanner);
			prog = (Chunk) p.parse().value;

		} catch (java.io.IOException e) {
			System.out.println("An I/O error occured while parsing : \n" + e);
			System.exit(1);
			/*
			 * XMLSerializer serializer = new XMLSerializer();
			 * 
			 * Chunk chunk = null;
			 * 
			 * assertXMLEqual(serializer.serialize(chunk),
			 * serializer.serialize(chunk));
			 */
		}
	}

}
