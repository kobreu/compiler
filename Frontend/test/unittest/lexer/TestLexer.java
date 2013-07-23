package unittest.lexer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
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

public class TestLexer {
	
	Lexer lexer;
	
	List<String> files = new ArrayList<String>();
	Map<String, List<Integer>> symbols = new HashMap<String, List<Integer>>();

	@Before
	public void setUp() {
		Integer[] symbolsForCase;
		String file;
		
		file = "testinput/grammar/chunk_with_return.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.RETURN, sym.WS, sym.ID, sym.COM, sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/lexer/assignment_with_spaces.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.WS, sym.ASM, sym.WS, sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/args_explist_none.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.ID, sym.LPAREN, sym.RPAREN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/args_explist_some.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.ID, sym.LPAREN, sym.NUMBER, sym.COM, sym.NUMBER, sym.RPAREN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/args_string.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.ID, sym.LPAREN, sym.TEXT, sym.RPAREN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/args_tableconstructor.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.ID, sym.LPAREN, sym.LCURL, sym.NUMBER, sym.COM, sym.NUMBER, sym.RCURL, sym.RPAREN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/binop_dotdot.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.CONCAT, sym.NUMBER };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/block.lua";
		files.add(file);
		symbolsForCase = new Integer[] {  };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_incorrect_multiple_returns.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.RETURN, sym.WS, sym.ID, sym.COM, sym.ID, sym.WS, sym.RETURN, sym.WS, sym.ID, sym.COM, sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_sequence_with_semicolons.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.SEMI, sym.ID, sym.ASM, sym.NUMBER, sym.SEMI, sym.ID, sym.ASM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_sequence_without_semicolons.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.ID, sym.ASM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_with_return.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.ID, sym.ASM, sym.NUMBER, sym.WS, sym.RETURN, sym.WS, sym.ID, sym.COM, sym.ID, sym.WS, sym.RETURN, sym.WS, sym.ID, sym.COM, sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_binop.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.ADD, sym.NUMBER };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_dotdotdot.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.PARAMS };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
	}
	
	@Test
	public void testNext_token() throws IOException {
		
		for(String file : files) {
			System.out.println(file);
			lexer = new Lexer(new java.io.FileReader(file));
			for(int i : symbols.get(file)) {
				Symbol curr = lexer.next_token();
				assertEquals(i, curr.sym);
			}
			// do it once again to check that all tokens have be read
			assertEquals(0, lexer.next_token().sym);
		}
		
		
		
	}

}
