package unittest.parser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.Symbol;

import org.custommonkey.xmlunit.XMLTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.xml.sax.SAXException;

import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;
import serialization.XMLDeserializer;
import serialization.XMLSerializer;

public class TestParserXML extends XMLTestCase {

	Parser parser;
	Lexer lexer;
	
	String[] xmlFiles;
	String[] luaFiles;
	
    @Rule
    public ErrorCollector collector = new ErrorCollector();

	
	
	@Before
	public void setUp() {
		List<String> xmlFiles = new ArrayList<>();
		List<String> luaFiles = new ArrayList<>();
		
		xmlFiles.add("testinput/grammar/chunk_with_return.xml");
		luaFiles.add("testinput/grammar/chunk_with_return.lua");
		
		xmlFiles.add("testinput/grammar/explist_tableconstructor.xml");
		luaFiles.add("testinput/grammar/explist_tableconstructor.lua");
		
		xmlFiles.add("testinput/grammar/field_name.xml");
		luaFiles.add("testinput/grammar/field_name.lua");
		
		/*xmlFiles.add("testinput/grammar/exp_function_application.xml");
		luaFiles.add("testinput/grammar/exp_function_application.lua");*/
		
		xmlFiles.add("testinput/grammar/function_funcbody.xml");
		luaFiles.add("testinput/grammar/function_funcbody.lua");
		
		xmlFiles.add("testinput/grammar/chunk_sequence_without_semicolons.xml");
		luaFiles.add("testinput/grammar/chunk_sequence_without_semicolons.lua");
		
		xmlFiles.add("testinput/grammar/chunk_sequence_with_semicolons.xml");
		luaFiles.add("testinput/grammar/chunk_sequence_with_semicolons.lua");
		
		xmlFiles.add("testinput/grammar/block.xml");
		luaFiles.add("testinput/grammar/block.lua");
		
		xmlFiles.add("testinput/grammar/exp_tableconstructor.xml");
		luaFiles.add("testinput/grammar/exp_tableconstructor.lua");

		xmlFiles.add("testinput/grammar/args_explist_none.xml");
		luaFiles.add("testinput/grammar/args_explist_none.lua");
		
		xmlFiles.add("testinput/grammar/args_explist_some.xml");
		luaFiles.add("testinput/grammar/args_explist_some.lua");

		xmlFiles.add("testinput/grammar/args_string.xml");
		luaFiles.add("testinput/grammar/args_string.lua");
		
		xmlFiles.add("testinput/grammar/args_tableconstructor.xml");
		luaFiles.add("testinput/grammar/args_tableconstructor.lua");
		
		xmlFiles.add("testinput/grammar/binop_concat.xml");
		luaFiles.add("testinput/grammar/binop_concat.lua");
		
		xmlFiles.add("testinput/grammar/explist_multiple.xml");
		luaFiles.add("testinput/grammar/explist_multiple.lua");
		
		this.xmlFiles = xmlFiles.toArray(new String[]{});
		this.luaFiles = luaFiles.toArray(new String[]{});
	}
	
	
	@Test
	public void test() throws Exception {
		XMLDeserializer deserializer = new XMLDeserializer();
		XMLSerializer serializer = new XMLSerializer();
        SAXReader reader = new SAXReader();
        reader.setStripWhitespaceText(true);
        reader.setMergeAdjacentText(true);

        Document document;
		
		for(int i = 0 ; i < xmlFiles.length; i++) {
			String xmlFile = xmlFiles[i];
			String luaFile = luaFiles[i];
			System.out.println(luaFile);
			// check if the file deserializes correctly
			Block b = (Block) deserializer.deserialize(xmlFile);
			assertNotNull("Block should not be null for file " + luaFile, b);

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
			
			document = reader.read(xmlFile);
			document.normalize();
			
			// remove Null nodes
			List<? extends Node> nodes = document.selectNodes("//Null");
			for(Node n : nodes) {
				n.detach();
			}

	        OutputFormat format = OutputFormat.createPrettyPrint();
	        XMLWriter writer = new XMLWriter( System.out, format );
	        //writer.write( doc );

	        //writer.write( document);
	        
	        assertXMLEqual(xmlFile, doc.asXML(), document.asXML());

		}
		
	}

}
