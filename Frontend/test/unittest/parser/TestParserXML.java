package unittest.parser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.custommonkey.xmlunit.XMLTestCase;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

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
	
	
	@Before
	public void setUp() {
		List<String> xmlFiles = new ArrayList<>();
		List<String> luaFiles = new ArrayList<>();
		
		xmlFiles.add("testinput/grammar/args_explist_none.xml");
		luaFiles.add("testinput/grammar/args_explist_none.lua");
		
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
        Document document;
		
		for(int i = 0 ; i < xmlFiles.length; i++) {
			String xmlFile = xmlFiles[i];
			String luaFile = luaFiles[i];
			// check if the file deserializes correctly
			Block b = (Block) deserializer.deserialize(xmlFile);
			assertNotNull("Block should not be null for file " + luaFile, b);
			
			lexer = new Lexer(new FileReader(luaFile));
			parser = new Parser(lexer);
			
			//Block block = (Block) parser.parse().value;
			
			//Document doc = serializer.serialize(block);
			
			//document = reader.read(xmlFile);

			//assertXMLEqual(doc.asXML(), document.asXML());
		}
		
	}

}
