package edu.tum.lua.stdlib.io;

import static edu.tum.lua.Preconditions.checkArguments;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;
import edu.tum.lua.types.LuaUserData;

public class FileRead extends LuaFunctionNative {

	private static final LuaType[][] types = { { LuaType.USERDATA }, { LuaType.STRING, LuaType.NUMBER, null } };
	private LuaUserData file;

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);
		file = (LuaUserData) arguments.get(0);

		if (file.getFm().equals("a") || file.getFm().equals("w")) {
			return Arrays.asList((Object) null, "Bad file descriptor");
		}

		if (arguments.size() < 2) {
			return Collections.singletonList((Object) readNextLine());
		}
		Object arg = arguments.get(1);
		switch (LuaType.getTypeOf(arg)) {
		case STRING:
			switch ((String) arg) {
			case "*n":
				return Collections.singletonList((Object) readNumber());
			case "*a":
				return Collections.singletonList((Object) readAll());
			case "*l":
				return Collections.singletonList((Object) readNextLine());
			default:
				throw new LuaBadArgumentException(1, "read", "invalid option");
			}
		case NUMBER:
			return Collections.singletonList((Object) readNChars((double) arg));
		default:
			throw new LuaBadArgumentException(1, "read", "invalid option");
		}
	}

	private String readAll() {
		long fileLength = -1;
		String fileContent = null;
		try {
			fileLength = file.getRaf().length();
			byte[] fileBytes = new byte[(int) fileLength];
			file.getRaf().read(fileBytes);
			fileContent = new String(fileBytes);
		} catch (EOFException eof) {
			return "";
		} catch (IOException e) {
			return null;
		}
		return fileContent;
	}

	private Double readNumber() {
		long pos = -1;
		try {
			pos = file.getRaf().getFilePointer();
		} catch (IOException e) {
			return null;
		}

		String s = "", n = "";
		do {
			s += n;
			n = readNChars(1.0);
		} while ((s + n).matches("[0-9]*(\\.[0-9]*)?"));

		if (!s.isEmpty()) {
			pos = pos + s.length();
		}

		try {
			file.getRaf().seek(pos);
		} catch (IOException e) {
			return null;
		}

		if (s.isEmpty()) {
			return null;
		}

		return new Double(Double.parseDouble(s));
	}

	private String readNChars(double n) {
		int numOfChars = (int) Math.floor(n);
		String chars = "";
		for (int i = 0; i < numOfChars; i++) {
			try {
				int next = file.getRaf().read();
				if (next == -1) {
					return null;
				}
				chars += (char) next;
			} catch (IOException e) {
				return null;
			}
		}
		return chars;
	}

	private String readNextLine() {
		String line = "";
		try {
			line = file.getRaf().readLine();
		} catch (EOFException eof) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return line;
	}
}
