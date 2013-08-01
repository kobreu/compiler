package location;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Test;

import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.SyntaxNode;
import edu.tum.lua.ast.VisitorNode;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;
import serialization.VisitorAdapterGeneric;

public class TestLocation {

	@Test
	public void test() throws Exception {
		String code = "function blub() end";
		//String code = "a=b";
		Lexer scanner = new Lexer(new StringReader(code));
		Block prog = null;

		Parser p = new Parser(scanner);
		try {
			prog = (Block) p.parse().value;
			
			prog.accept(new VisitorAdapterGeneric() {
				
				@Override
				public void visitGeneric(VisitorNode node) {
					Location start = ((SyntaxNode) node).getStart();
					Location marker =((SyntaxNode) node).getMarker();
					Location end = ((SyntaxNode) node).getEnd();
					System.out.print(node.getClass().getSimpleName());
					if(start != null) System.out.print(" Start: " + start.getRow() + " " + start.getColumn());
					if(marker != null) System.out.print (" Marker: " + marker.getRow() + " " + marker.getColumn());
					if(end != null) System.out.print(" End: " + end.getRow() + " " + end.getColumn());
					System.out.println();
					node.childrenAccept(this);
				}
			});
		} catch(Exception ex) { 
			ex.printStackTrace();
		}
		
	}

}
