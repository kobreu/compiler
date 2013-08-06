package edu.tum.juna.junit.parser.location;

import java.io.StringReader;

import org.junit.Test;

import edu.tum.juna.ast.Block;
import edu.tum.juna.ast.SyntaxNode;
import edu.tum.juna.ast.VisitorNode;
import edu.tum.juna.parser.Lexer;
import edu.tum.juna.parser.Parser;
import edu.tum.juna.parser.location.Location;
import edu.tum.juna.parser.serialization.VisitorAdapterGeneric;

public class TestLocation {

	@Test
	public void test() throws Exception {
		String code = "function blub() end";
		// String code = "a=b";
		Lexer scanner = new Lexer(new StringReader(code));
		Block prog = null;

		Parser p = new Parser(scanner);
		try {
			prog = (Block) p.parse().value;

			prog.accept(new VisitorAdapterGeneric() {

				@Override
				public void visitGeneric(VisitorNode node) {
					Location start = ((SyntaxNode) node).getStart();
					Location marker = ((SyntaxNode) node).getMarker();
					Location end = ((SyntaxNode) node).getEnd();
					System.out.print(node.getClass().getSimpleName());
					if (start != null)
						System.out.print(" Start: " + start.getRow() + " " + start.getColumn());
					if (marker != null)
						System.out.print(" Marker: " + marker.getRow() + " " + marker.getColumn());
					if (end != null)
						System.out.print(" End: " + end.getRow() + " " + end.getColumn());
					System.out.println();
					node.childrenAccept(this);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
