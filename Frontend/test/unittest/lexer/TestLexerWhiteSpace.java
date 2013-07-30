package unittest.lexer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java_cup.runtime.Symbol;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.sym;

public class TestLexerWhiteSpace {
	
	Lexer lexer;
	
	List<String> files = new ArrayList<String>();
	Map<String, List<Integer>> symbols = new HashMap<String, List<Integer>>();

	@Before
	public void setUp() {
		Integer[] symbolsForCase;
		String file;
		
		file = "testinput/lexer/assignment_with_spaces.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID,  sym.ASM,  sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/lexer/multiple_spaces";
		files.add(file);
		symbolsForCase = new Integer[] {  };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/lexer/multiple_spaces_with_breaks";
		files.add(file);
		symbolsForCase = new Integer[] {  sym.ID,  sym.ID,  sym.ID,  sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/lexer/multiple_spaces_with_strings";
		files.add(file);
		symbolsForCase = new Integer[] {  sym.TEXT,  sym.TEXT, sym.TEXT,  sym.TEXT};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/lexer/escaping";
		files.add(file);
		symbolsForCase = new Integer[] { sym.TEXT,  sym.TEXT,  sym.TEXT,  sym.TEXT};
		symbols.put(file, Arrays.asList(symbolsForCase));
	}
	@Test
	public void testNext_token() throws IOException {
		for(String file : files) {
			System.out.println(file);
			lexer = new Lexer(new java.io.FileReader(file));
			List<Integer> symbolsForFile = symbols.get(file);
			for(int i : symbolsForFile) {
				Symbol curr = lexer.next_token();
				System.out.println(i + " " + curr.sym);
				assertEquals(i, curr.sym);
			}
			// do it once again to check that all tokens have be read
			assertEquals(0, lexer.next_token().sym);
		}
	}

}
