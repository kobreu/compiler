package serialization;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.transform.TransformerException;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;

import edu.tum.lua.ast.Block;

public class TestXMLDeserializer extends XMLTestCase {

	@Test
	public void testDeserialize() throws TransformerException {
		XMLDeserializer xml = new XMLDeserializer();
		
		Block block = (Block) xml.deserialize(new File("testing.xml"));
	}
	
	

}
