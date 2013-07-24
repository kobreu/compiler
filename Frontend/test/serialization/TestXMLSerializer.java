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
import edu.tum.lua.ast.Chunk;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.LastStat;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.StatList;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Visitor;

public class TestXMLSerializer extends XMLTestCase {

	@Test
	public void testSerialize() throws TransformerException {
		Chunk chunk = new Chunk(new StatList(), new LastReturn(new ExpList(
				new Unop(Op.UNM, new NumberExp(1.0)))));

		XMLSerializer serializer = new XMLSerializer();

		Document doc = serializer.serialize(chunk);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File("testing.xml"));
		transformer.transform(source, result);

		System.out.println("Done");
	}
	
	@Test
	public void testCompare() throws ParserConfigurationException, SAXException, IOException {
		Chunk chunk = new Chunk(new StatList(), new LastReturn(new ExpList(
				new Unop(Op.UNM, new NumberExp(1.0)))));
		
		XMLSerializer serializer = new XMLSerializer();
		
		Document doc = serializer.serialize(chunk);
		
		File fXmlFile = new File("testing.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc2 = dBuilder.parse(fXmlFile);
		
		assertXMLEqual(doc, doc2);
	}
	

}
