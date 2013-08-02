package demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import java_cup.runtime.Symbol;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import serialization.XMLDeserializer;
import serialization.XMLSerializer;
import unittest.parser.TestParser;
import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;

public class TestSmallFile {
	

	static Parser parser;
	static Lexer lexer;
	
	
	public static void main(String[] args) throws Exception {
		XMLDeserializer deserializer = new XMLDeserializer();
		XMLSerializer serializer = new XMLSerializer();
        SAXReader reader = new SAXReader();
        reader.setStripWhitespaceText(true);
        reader.setMergeAdjacentText(true);

        Document document;
		
			String luaFile = "test/demo/smallfile.lua";
			System.out.println(luaFile);
			// check if the file deserializes correctly

			Lexer outputScanner = new Lexer(new java.io.FileReader(luaFile));

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
	        XMLWriter writer = new XMLWriter( System.out, format );
	        writer.write( doc );

	}

}
