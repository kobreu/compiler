package unittest.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import java_cup.runtime.Symbol;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;

public class TestSimpleRun {

	ArrayList<String> testFiles = new ArrayList<>();

	@Before
	public void setUp() {
		// reference test suite
		// outcommented does not work yet
		testFiles.add("testinput/lua5.1-tests/all.lua");
		testFiles.add("testinput/lua5.1-tests/api.lua");
		testFiles.add("testinput/lua5.1-tests/attrib.lua");
		testFiles.add("testinput/lua5.1-tests/big.lua");
		testFiles.add("testinput/lua5.1-tests/calls.lua");
		testFiles.add("testinput/lua5.1-tests/checktable.lua");
		testFiles.add("testinput/lua5.1-tests/closure.lua");
		testFiles.add("testinput/lua5.1-tests/code.lua");
		testFiles.add("testinput/lua5.1-tests/constructs.lua");
		testFiles.add("testinput/lua5.1-tests/db.lua");
		testFiles.add("testinput/lua5.1-tests/errors.lua");
		testFiles.add("testinput/lua5.1-tests/events.lua");
		testFiles.add("testinput/lua5.1-tests/files.lua");
		testFiles.add("testinput/lua5.1-tests/gc.lua");
		testFiles.add("testinput/lua5.1-tests/literals.lua");
		testFiles.add("testinput/lua5.1-tests/locals.lua");
		testFiles.add("testinput/lua5.1-tests/main.lua");
		testFiles.add("testinput/lua5.1-tests/math.lua");
		testFiles.add("testinput/lua5.1-tests/nextvar.lua");
		testFiles.add("testinput/lua5.1-tests/pm.lua");
		testFiles.add("testinput/lua5.1-tests/sort.lua");
		testFiles.add("testinput/lua5.1-tests/strings.lua");
		testFiles.add("testinput/lua5.1-tests/vararg.lua");
		testFiles.add("testinput/lua5.1-tests/verybig.lua");
		
		
		testFiles.add("testinput/homework/korbinian_sorting.lua");
		
		testFiles.add("testinput/grammar/korbinian_1.lua");
		testFiles.add("testinput/homework/salomon_2sat/runSelfCheck.lua");

		testFiles.add("testinput/homework/salomon_2sat/formula.lua");
		
		testFiles.add("testinput/homework/salomon_2sat/graph.lua");
		
		testFiles.add("testinput/homework/lisa_avltree/avltree.lua");
		
		testFiles.add("testinput/homework/lisa_avltree/test.lua");

		testFiles.add("testinput/homework/johannes_parser.lua");

	}

	@Test
	public void test() throws Exception {
		for (String file : testFiles) {

			Lexer scanner = null;
			Lexer outputScanner = null;
			try {
				outputScanner = new Lexer(new java.io.FileReader(file));

				// print out the tokens
				Symbol token;
				token = outputScanner.next_token();
				while (token.sym != 0) {
					System.out.print(TestParser.tokenLookup(token.sym) + " ");
					token = outputScanner.next_token();
				}
				System.out.println();

				scanner = new Lexer(new java.io.FileReader(file));
			} catch (java.io.FileNotFoundException e) {
				System.out.println("File not found");
				System.exit(1);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Usage : java Main <inputfile>");
				System.exit(1);
			}

			Block prog = null;

			try {
				Parser p = new Parser(scanner);
				prog = (Block) p.parse().value;
				//System.out.println(prog.toString());

			} catch (java.io.IOException e) {
				System.out.println("An I/O error occured while parsing : \n"
						+ e);
				System.exit(1);
				/*
				 * XMLSerializer serializer = new XMLSerializer();
				 * 
				 * Chunk chunk = null;
				 * 
				 * assertXMLEqual(serializer.serialize(chunk),
				 * serializer.serialize(chunk));
				 */
			} catch(Exception ex) {
				
			}
		}

	}

}
