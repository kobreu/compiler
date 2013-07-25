package serialization;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.XMLTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.sun.beans.decoder.DocumentHandler;
import com.sun.org.apache.xpath.internal.operations.Number;

import edu.tum.lua.ast.Asm;
import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.ExpList;
import edu.tum.lua.ast.LastReturn;
import edu.tum.lua.ast.LastStat;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.StatList;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.VarList;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.Visitor;

public class TestXMLSerializer extends XMLTestCase {

	@Test
	public void testSerialize() throws TransformerException, IOException {
		Block chunk = new Block(new StatList(), new LastReturn(new ExpList(
				new Unop(Op.UNM, new NumberExp(1.0)))));
		
		XMLSerializer serializer = new XMLSerializer();

		Document doc = serializer.serialize(chunk);
		
		// Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter( System.out, format );
        writer.write( doc );
        writer.close();

		System.out.println("Done");
	}
	
	@Test
	public void testMoreComplete() throws TransformerException, IOException {
	    Block chunk = new Block(new StatList(new Asm(new VarList(new Variable("var")), new ExpList(new Binop(new NumberExp(1.0), Op.ADD,  new NumberExp(5.0))) )), new LastReturn(new ExpList(
				new Unop(Op.UNM, new NumberExp(1.0)))));
		
		XMLSerializer serializer = new XMLSerializer();

		Document doc = serializer.serialize(chunk);

		// Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter( System.out, format );
        writer.write( doc );


		System.out.println("Done");
	}
	
	
	@Test
	public void testTest() {
	}
	

}
