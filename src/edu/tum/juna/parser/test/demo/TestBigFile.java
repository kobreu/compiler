package edu.tum.juna.parser.test.demo;

import java.io.FileReader;

import java_cup.runtime.Symbol;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import edu.tum.juna.ast.Block;
import edu.tum.juna.junit.parser.unittest.parser.TestParser;
import edu.tum.juna.parser.Lexer;
import edu.tum.juna.parser.Parser;
import edu.tum.juna.parser.serialization.XMLSerializer;

public class TestBigFile {

	static Parser parser;
	static Lexer lexer;

	public static void main(String[] args) throws Exception {
		XMLSerializer serializer = new XMLSerializer();
		SAXReader reader = new SAXReader();
		reader.setStripWhitespaceText(true);
		reader.setMergeAdjacentText(true);

		String luaFile = "test/edu.tum.juna.parser.test.demo/bigfile.lua";
		System.out.println(luaFile);
		// check if the file deserializes correctly

		try (java.io.FileReader in = new java.io.FileReader(luaFile)) {
			Lexer outputScanner = new Lexer(in);

			// print out the tokens
			Symbol token;
			token = outputScanner.next_token();
			while (token.sym != 0) {
				System.out.print(TestParser.tokenLookup(token.sym) + " ");
				token = outputScanner.next_token();
			}

			System.out.println();

			lexer = new Lexer(new FileReader(luaFile));
			parser = new Parser(lexer);

			Block block = (Block) parser.parse().value;

			Document doc = serializer.serialize(block);
			doc.normalize();

			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out, format);
			writer.write(doc);
		}
	}

}
