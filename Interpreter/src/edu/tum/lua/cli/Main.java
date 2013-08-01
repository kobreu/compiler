package edu.tum.lua.cli;

import util.ParserUtil;
import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LuaInterpreter;

public class Main {

	public static void main(String... args) throws Exception {

		CommandLine cmd = null;

		if (args.length > 0) {
			switch (args[0]) {
			case "-h":
			case "-?":
			case "-help":
				System.out.println("Not implemented yet");
				return;
			case "-i":
				cmd = new CommandLine();
				if (args.length >= 2) {
					cmd.doFile(args[1]);
				}
				break;
			case "-f":
				if (args.length < 2) {
					System.out.println("with -f you need to specify a second argument. See -h for help");
					return;
				}
				LuaInterpreter.eval(ParserUtil.loadFile(args[1]), new GlobalEnvironment());
				return;
			default:
				LuaInterpreter.eval(ParserUtil.loadFile(args[0]), new GlobalEnvironment());
				return;
			}
		}

		cmd.run();
	}
}
