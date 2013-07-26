package main;

import edu.tum.lua.ast.Block;
import util.ParserUtil;

public class UsageExample {

	public static void main(String[] args) {
		try {
			Block b = ParserUtil.parse("testinput/grammar/chunk_sequence_with_semicolons.lua");
			Block b2 = ParserUtil.parse("testinput/grammar/exp_nil.lua");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
