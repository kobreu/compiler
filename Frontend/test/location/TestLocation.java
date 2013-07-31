package location;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Test;

import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;
import edu.tum.lua.parser.exception.StatementNotFinishedException;
import edu.tum.lua.parser.exception.SyntaxError;
import util.ParserUtil;

public class TestLocation {

	@Test
	public void test() throws Exception {
		String code = "function blub() end";
		Lexer scanner = new Lexer(new StringReader(code));
		Block prog = null;

		Parser p = new Parser(scanner);
		try {
			prog = (Block) p.parse().value;
		} catch(Exception ex) { 
			ex.printStackTrace();
		}
		
	}

}
