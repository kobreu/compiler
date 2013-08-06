package main;

import util.ParserUtil;

public class UsageExample {

	public static void main(String[] args) {
		try {
			ParserUtil.loadFile("testinput/grammar/chunk_sequence_with_semicolons.lua");
			ParserUtil.loadFile("testinput/grammar/exp_nil.lua");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
