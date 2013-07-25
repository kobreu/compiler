package serialization;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sun.org.apache.xpath.internal.operations.Number;

import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.Chunk;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.LastStat;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.StatList;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Visitor;

public class TestXMLDeserializer extends XMLTestCase {

	@Test
	public void testDeserialize() throws TransformerException {
		XMLDeserializer xml = new XMLDeserializer();
		
		Block block = (Block) xml.deserialize(new File("testing.xml"));
	}
	
	

}
