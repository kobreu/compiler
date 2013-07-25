package serialization;

import java.util.Enumeration;

import javax.net.ssl.SSLEngineResult.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.VisitorNode;

public class XMLSerializer {
	
	private Document doc;
	
	private Element currElement;
	
	private class XMLVisitor extends VisitorAdapterGeneric {
		
		private void appendToCurrAndDive(String tagname) {
			Element el = currElement.addElement(tagname);
			currElement = el;
		}
		
		private void appendToCurrAndSwim(String tagname) {
			currElement.addElement(tagname);
		}
		
		private void lift() {
			currElement = currElement.getParent();
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
		doc = DocumentHelper.createDocument();
		doc.setName("Block");
		
		Element chunkE = doc.addElement(chunk.getClass().getSimpleName());
		currElement = chunkE;
		
		// create root node for the block
		XMLVisitor visitor = new XMLVisitor();
		chunk.childrenAccept(visitor);

		return doc;
	}

}
