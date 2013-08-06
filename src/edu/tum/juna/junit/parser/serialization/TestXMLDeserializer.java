package edu.tum.juna.junit.parser.serialization;

import java.io.File;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;

import edu.tum.juna.parser.serialization.XMLDeserializer;

public class TestXMLDeserializer extends XMLTestCase {

	@Test
	public void testDeserialize() {
		XMLDeserializer xml = new XMLDeserializer();

		xml.deserialize(new File("testinput/grammar/args_explist_none.xml"));
	}
}
