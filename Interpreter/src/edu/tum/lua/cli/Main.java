package edu.tum.lua.cli;

import util.ParserUtil;
import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.LuaInterpreter;

public class Main {

	public static void main(String... args) throws Exception {

		CommandLine cmd = new CommandLine();

		if (args.length > 0) {
			switch (args[0]) {
			case "-h":
			case "-?":
			case "-help":
				printHelp();
				return;
			case "-i":
				if (args.length >= 2) {
					cmd.doFile(args[1]);
				}
				break;
			case "-f":
				if (args.length < 2) {
					System.out.println("with -f you need to specify a second argument. See -h for help");
					return;
				}
				if (args.length > 2) {
					String[] arg = new String[args.length - 2];
					for (int i = 2; i < args.length; i++) {
						arg[i - 2] = args[i];
					}
					LuaInterpreter.eval(ParserUtil.loadFile(args[1]), new GlobalEnvironment(arg));
				} else {
					LuaInterpreter.eval(ParserUtil.loadFile(args[1]), new GlobalEnvironment());
				}
				return;
			default:
				if (args.length > 1) {
					String[] arg = new String[args.length - 1];
					for (int i = 1; i < args.length; i++) {
						arg[i - 1] = args[i];
					}
					LuaInterpreter.eval(ParserUtil.loadFile(args[0]), new GlobalEnvironment(arg));
				} else {
					LuaInterpreter.eval(ParserUtil.loadFile(args[0]), new GlobalEnvironment());
				}
				return;
			}
		}

		cmd.run();
	}

	private static void printHelp() {
		System.out
				.println("Java based Lua Interpreter\nTUM CS CC-Labcourse 2013 K. Breu, I. Delesques-Grauby, M. Kneidel, J. Mikulasch, S. Padma, S. Sickert, L. Velden");
		System.out.println("\nUsage: java -jar lua.jar [-options] [lua-File]\nwith following options:");

		System.out.println("\t-help, -h, -?\t\tShow this help");
		System.out
				.println("\t-i [<File>]\t\tStarts the Interpreter in interactive mode and if a Lua-File is given as argument execute it before");
		System.out
				.println("\t[-f] <File>\t\tExecutes the Lua-File given as argument and exit the Interpreter afterwards");

		System.out
				.println("\nFor Help concerning Lua see the User Manual http://www.lua.org/manual/5.1/manual.html or --help in the interactive mode");
		System.out
				.println("\nFor more Informations see our project on https://github.com/kobreu/compiler or cantact us on cc-labcourse@t-online.de");
	}
}
