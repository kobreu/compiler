package unittest.parser;

import static org.junit.Assert.*;
import java_cup.runtime.Symbol;

import org.junit.Test;

import serialization.XMLSerializer;
import edu.tum.lua.ast.Block;
import edu.tum.lua.parser.*;

public class TestParser {

	@Test
	public void test() throws Exception {
		String file = "testinput/grammar/chunk_sequence_with_semicolons.lua";

		Lexer scanner = null;
		Lexer outputScanner = null;
		try {
			outputScanner = new Lexer(new java.io.FileReader(file));

			// print out the tokens
			Symbol token;
			token = outputScanner.next_token();
			while (token.sym != 0) {
				System.out.print(tokenLookup(token.sym) + " ");
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
			System.out.println(prog.toString());

		} catch (java.io.IOException e) {
			System.out.println("An I/O error occured while parsing : \n" + e);
			System.exit(1);
			/*
			 * XMLSerializer serializer = new XMLSerializer();
			 * 
			 * Chunk chunk = null;
			 * 
			 * assertXMLEqual(serializer.serialize(chunk),
			 * serializer.serialize(chunk));
			 */
		}
	}

	private String tokenLookup(int symb) {
		switch (symb) {
		case sym.ADD:
			return "ADD";
		case sym.AND:
			return "AND";
		case sym.ASM:
			return "ASM";
		case sym.BREAK:
			return "BREAK";
		case sym.COM:
			return "COM";
		case sym.CONCAT:
			return "CONCAT";
		case sym.DDOT:
			return "DDOT";
		case sym.DIV:
			return "DIV";
		case sym.DO:
			return "DO";
		case sym.DOT:
			return "DOT";
		case sym.ELSE:
			return "ELSE";
		case sym.ELSEIF:
			return "ELSEIF";
		case sym.END:
			return "END";
		case sym.EQ:
			return "EQ";
		case sym.FALSE:
			return "FALSE";
		case sym.FOR:
			return "FOR";
		case sym.FUNC:
			return "FUNC";
		case sym.GEQ:
			return "GEQ";
		case sym.GR:
			return "GR";
		case sym.ID:
			return "ID";
		case sym.IF:
			return "IF";
		case sym.IN:
			return "IN";
		case sym.LBRACK:
			return "LBRACK";
		case sym.LCURL:
			return "LCURL";
		case sym.LE:
			return "LE";
		case sym.LENGTH:
			return "LENGTH";
		case sym.LEQ:
			return "LEQ";
		case sym.MOD:
			return "MOD";
		case sym.MUL:
			return "MUL";
		case sym.NEQ:
			return "NEQ";
		case sym.NIL:
			return "NIL";
		case sym.NOT:
			return "NOT";
		case sym.NUMBER:
			return "NUMBER";
		case sym.OR:
			return "OR";
		case sym.SEMI:
			return "SEMI";
		case sym.WS:
			return "WS";
		default:
			return "UNKNOWN";
		}
	}

}
