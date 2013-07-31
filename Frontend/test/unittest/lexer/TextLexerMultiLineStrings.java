package unittest.lexer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;

import org.junit.Test;

import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.sym;

public class TextLexerMultiLineStrings {

	@Test
	public void test() throws IOException {
		Lexer lexer;
		String file;
		
		file = "testinput/lexer/multiline_string.lua";
		lexer = new Lexer(new FileReader(file));
		
		Integer[] symbolsForCase = { sym.ID, sym.ASM, sym.TEXT};
		String[] strings =  { "I am a \nmultiline string"};
		
		int j = 0;
		for(int i = 0; i < symbolsForCase.length; i++) {
			Symbol curr = lexer.next_token();
			//System.out.println(symbolsForCase[i] + " " + curr.sym);
			assertEquals((int) symbolsForCase[i], curr.sym);
			if(curr.sym == sym.TEXT) {
				//System.out.println(strings[j] + " " + curr.value);
				assertEquals(strings[j], curr.value);
				j++;
			}
		}

	}

}
