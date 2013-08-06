package edu.tum.juna.parser.serialization;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import edu.tum.juna.ast.Block;
import edu.tum.juna.ast.VisitorNode;

public class XMLSerializer {

	private Document doc;

	Element currElement;

	class XMLVisitor extends VisitorAdapterGeneric {

		private void appendToCurrAndDive(String tagname) {
			Element el = currElement.addElement(tagname);
			currElement = el;
		}

		private void lift() {
			currElement = currElement.getParent();
		}

		private void addAttributes(VisitorNode node) {
			node.accept(new AttributeWriterVisitor(currElement));
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

		if (chunk == null) {
			return doc;
		}

		Element chunkE = doc.addElement(chunk.getClass().getSimpleName());
		currElement = chunkE;

		// create root node for the block
		XMLVisitor visitor = new XMLVisitor();
		chunk.childrenAccept(visitor);

		return doc;
	}

}
