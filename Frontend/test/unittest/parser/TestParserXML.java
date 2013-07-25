package unittest.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.ast.Block;
import serialization.XMLDeserializer;

public class TestParserXML {


	List<String> xmlFiles = new ArrayList<>();
	
	@Before
	public void setUp() {
		xmlFiles.add("testinput/grammar/args_explist_none.xml");
	}
	
	@Test
	public void test() {
		XMLDeserializer deserializer = new XMLDeserializer();
		
		for(String file : xmlFiles) {
			// check if the file deserializes correctly
			Block b = (Block) deserializer.deserialize(file);
		}
		
	}

}
