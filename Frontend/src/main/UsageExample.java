package main;

import util.ParserUtil;
import edu.tum.lua.ast.Block;

public class UsageExample {

	public static void main(String[] args) {
		try {
			Block b = ParserUtil.loadFile("testinput/grammar/chunk_sequence_with_semicolons.lua");
			Block b2 = ParserUtil.loadFile("testinput/grammar/exp_nil.lua");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
