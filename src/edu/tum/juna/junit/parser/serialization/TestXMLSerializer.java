package edu.tum.juna.junit.parser.serialization;

import java.io.IOException;

import org.custommonkey.xmlunit.XMLTestCase;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import edu.tum.juna.ast.Asm;
import edu.tum.juna.ast.Binop;
import edu.tum.juna.ast.Block;
import edu.tum.juna.ast.ExpList;
import edu.tum.juna.ast.LastReturn;
import edu.tum.juna.ast.NumberExp;
import edu.tum.juna.ast.Op;
import edu.tum.juna.ast.StatList;
import edu.tum.juna.ast.Unop;
import edu.tum.juna.ast.VarList;
import edu.tum.juna.ast.Variable;
import edu.tum.juna.parser.serialization.XMLSerializer;

public class TestXMLSerializer extends XMLTestCase {

	@Test
	public void testSerialize() throws IOException {
		Block chunk = new Block(new StatList(), new LastReturn(new ExpList(new Unop(Op.UNM, new NumberExp(1.0)))));

		XMLSerializer serializer = new XMLSerializer();

		Document doc = serializer.serialize(chunk);

		// Pretty print the document to System.out
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(System.out, format);
		writer.write(doc);
		writer.close();

		System.out.println("Done");
	}

	@Test
	public void testMoreComplete() throws IOException {
		Block chunk = new Block(new StatList(new Asm(new VarList(new Variable("var")), new ExpList(new Binop(
				new NumberExp(1.0), Op.ADD, new NumberExp(5.0))))), new LastReturn(new ExpList(new Unop(Op.UNM,
				new NumberExp(1.0)))));

		XMLSerializer serializer = new XMLSerializer();

		Document doc = serializer.serialize(chunk);

		// Pretty print the document to System.out
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(System.out, format);
		writer.write(doc);

		System.out.println("Done");
	}

	@Test
	public void testTest() {
	}

}
