package serialization;

import java.util.Enumeration;

import javax.net.ssl.SSLEngineResult.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.VisitorNode;

public class XMLSerializer {
	
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = null;
	private Document doc;
	
	private Node currElement;
	
	private class XMLVisitor extends VisitorAdapterGeneric {
		
		private void appendToCurrAndDive(String tagname) {
			Element el = doc.createElement(tagname);
			currElement.appendChild(el);
			currElement = el;
		}
		
		private void appendToCurrAndSwim(String tagname) {
			Element el = doc.createElement(tagname);
			currElement.appendChild(el);
		}
		
		private void lift() {
			currElement = currElement.getParentNode();
		}
		
		private void addAttributes(VisitorNode node) {
			node.accept(new AttributeWriterVisitor(currElement, doc));
		}
		
		
		@Override
		public void visitGeneric(VisitorNode node) {
			appendToCurrAndDive(node.getClass().getSimpleName());
			addAttributes(node);
			node.childrenAccept(this);
			lift();
		}

	}
	
	public Document serialize(Block chunk) {
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
		doc = docBuilder.newDocument();
		
		Element chunkE = doc.createElement(chunk.getClass().getSimpleName());
		doc.appendChild(chunkE);
		currElement = chunkE;
		
		// create root node for the block
		XMLVisitor visitor = new XMLVisitor();
		chunk.childrenAccept(visitor);

		return doc;
	}

}
