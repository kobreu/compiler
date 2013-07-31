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

import unittest.parser.TestParser;
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
		
		file = "testinput/lexer/multiline_comment.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.LPAREN, sym.TEXT, sym.RPAREN };
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
		
		file = "testinput/binop/binop_concat.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.CONCAT, sym.NUMBER };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/block.lua";
		files.add(file);
		symbolsForCase = new Integer[] {  };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_incorrect_multiple_returns.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER,  sym.ID, sym.ASM, sym.NUMBER,  sym.RETURN,  sym.ID, sym.COM, sym.ID,  sym.RETURN,  sym.ID, sym.COM, sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_sequence_with_semicolons.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.SEMI, sym.ID, sym.ASM, sym.NUMBER, sym.SEMI, sym.ID, sym.ASM, sym.NUMBER, sym.SEMI};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_sequence_without_semicolons.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER,  sym.ID, sym.ASM, sym.NUMBER,  sym.ID, sym.ASM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/chunk_with_return.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER,  sym.ID, sym.ASM, sym.NUMBER,  sym.RETURN,  sym.ID, sym.COM, sym.ID };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_binop.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER, sym.ADD, sym.NUMBER };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_dotdotdot.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.PARAMS };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_false.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.FALSE };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_function.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.FUNC,  sym.LPAREN, sym.RPAREN,  sym.END };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_nil.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NIL };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_number.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_prefixexp.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.LPAREN, sym.NIL, sym.RPAREN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_string.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.TEXT };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_tableconstructor.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.LCURL, sym.NUMBER, sym.COM, sym.NUMBER, sym.COM, sym.NUMBER, sym.RCURL };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_true.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.TRUE };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/exp_unop.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.SUB, sym.NUMBER };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/explist_multiple.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.FOR,  sym.ID,  sym.IN,  sym.NUMBER, sym.COM, sym.NUMBER, sym.COM, sym.NUMBER,  sym.DO,  sym.END };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/field_exp.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.LCURL, sym.LBRACK, sym.NUMBER, sym.RBRACK, sym.ASM, sym.NUMBER, sym.RCURL };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/field_name.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.LCURL, sym.ID, sym.ASM, sym.NUMBER, sym.RCURL };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/fieldlist_separator_at_end.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.LCURL, sym.NUMBER, sym.SEMI, sym.NUMBER, sym.SEMI, sym.NUMBER, sym.COM, sym.NUMBER, sym.COM, sym.RCURL };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/funcname_add_to_table.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.FUNC,  sym.ID, sym.DOT, sym.ID, sym.DOT, sym.ID,  sym.LPAREN, sym.RPAREN,  sym.END };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/funcname_colon.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.FUNC,  sym.ID, sym.DOT, sym.ID, sym.DDOT, sym.ID,  sym.LPAREN, sym.RPAREN,  sym.END };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/function_funcbody.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.FUNC, sym.LPAREN, sym.RPAREN, sym.END };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/functioncall_prefixexp_colon.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.LPAREN, sym.NUMBER, sym.RPAREN, sym.DDOT, sym.ID, sym.LPAREN, sym.NUMBER, sym.RPAREN, sym.LPAREN, sym.NUMBER, sym.RPAREN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/functioncall_prefixexp.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.LPAREN, sym.NUMBER, sym.RPAREN, sym.LPAREN, sym.NUMBER, sym.RPAREN};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/laststat_break.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.BREAK };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/laststat_return_empty.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.RETURN };
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/laststat_return.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.RETURN ,  sym.ID, sym.COM, sym.ID};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/namelist_multiple.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FOR,  sym.ID, sym.COM, sym.ID, sym.COM, sym.ID,  sym.IN,  sym.NUMBER,  sym.DO,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/namelist_single.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FOR,  sym.ID,  sym.IN,  sym.NUMBER,  sym.DO,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/parlist_dotdotdot.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FUNC,  sym.ID, sym.LPAREN, sym.ID, sym.COM, sym.PARAMS, sym.RPAREN,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/prefixexp_brackets.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.ID, sym.ASM, sym.LPAREN, sym.NUMBER, sym.RPAREN};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/prefixexp_functioncall.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.ID, sym.ASM, sym.ID, sym.LPAREN, sym.RPAREN, sym.LBRACK, sym.NUMBER, sym.RBRACK};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/prefixexp_var.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.ID, sym.ASM, sym.ID, sym.LBRACK, sym.NUMBER, sym.RBRACK};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_assignment.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.ID, sym.COM, sym.ID, sym.ASM, sym.NUMBER, sym.COM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_do.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.DO,  sym.ID, sym.ASM, sym.NUMBER,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_for_without_last_expression.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FOR,  sym.ID,  sym.ASM,  sym.NUMBER, sym.COM,  sym.NUMBER,  sym.DO,  sym.ID, sym.ASM, sym.NUMBER,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_for.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FOR,  sym.ID,  sym.ASM,  sym.NUMBER, sym.COM,  sym.NUMBER,  sym.COM,  sym.NUMBER,  sym.DO,  sym.ID, sym.ASM, sym.NUMBER,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));

		file = "testinput/grammar/stat_foreach.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FOR,  sym.ID,  sym.IN,  sym.NUMBER,  sym.DO,  sym.ID, sym.ASM, sym.NUMBER,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_function.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.FUNC,  sym.ID,  sym.LPAREN, sym.RPAREN,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_functioncall.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.ID, sym.LPAREN, sym.RPAREN};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_local_assignment.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.LOCAL,  sym.ID, sym.COM, sym.ID,  sym.ASM,  sym.NUMBER, sym.COM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_local_function.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.LOCAL,  sym.FUNC,  sym.ID,  sym.LPAREN,sym.RPAREN,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_repeat.lua";
		files.add(file);
		symbolsForCase = new Integer[] {sym.REPEAT,  sym.ID, sym.ASM, sym.NUMBER,  sym.UNTIL,  sym.FALSE};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/stat_while.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.WHILE,  sym.TRUE,  sym.DO,  sym.ID, sym.ASM, sym.NUMBER,  sym.END};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/unop_hash.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.LENGTH, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/var_name.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/var_prefix_brackets.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.LBRACK, sym.NUMBER, sym.RBRACK, sym.ASM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/var_prefix_dot.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.DOT, sym.ID, sym.ASM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/varlist_multiple.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.COM, sym.ID, sym.ASM, sym.NUMBER, sym.COM, sym.NUMBER};
		symbols.put(file, Arrays.asList(symbolsForCase));
		
		file = "testinput/grammar/varlist_single.lua";
		files.add(file);
		symbolsForCase = new Integer[] { sym.ID, sym.ASM, sym.NUMBER};
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
				System.out.print(TestParser.tokenLookup(curr.sym) + " ");
				assertEquals(i, curr.sym);
			}
			System.out.println();
			// do it once again to check that all tokens have be read
			assertEquals(0, lexer.next_token().sym);
		}
		
		
		
	}

}
