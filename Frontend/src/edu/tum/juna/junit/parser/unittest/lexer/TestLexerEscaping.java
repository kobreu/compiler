package edu.tum.juna.junit.parser.unittest.lexer;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;

import org.junit.Test;

import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.sym;

public class TestLexerEscaping {

	@Test
	public void test() throws IOException {
		Lexer lexer;
		String file;
		
		file = "testinput/lexer/escaping_2";
		lexer = new Lexer(new FileReader(file));
		
		Integer[] symbolsForCase = { sym.TEXT,  sym.TEXT,  sym.TEXT,  sym.TEXT,  sym.TEXT,  sym.TEXT};
		String[] strings =  { "\n\r\t", "\\", "\"", "\n\r\t", "\\", "'"};
		
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
