package edu.tum.lua.stdlib.io;

import static edu.tum.lua.Preconditions.checkArguments;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import edu.tum.lua.LuaBadArgumentException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class FileRead extends LuaFunctionNative {

	private static final LuaType[][] types = { { LuaType.USERDATA },
			{ LuaType.STRING, LuaType.NUMBER, null } };
	private RandomAccessFile file;

	@Override
	public List<Object> apply(List<Object> arguments) {
		checkArguments("read", arguments, types);
		file = (RandomAccessFile) arguments.get(0);
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
			fileLength = file.length();
			byte[] fileBytes = new byte[(int) fileLength];
			file.read(fileBytes);
			fileContent = new String(fileBytes);
		} catch (EOFException eof) {
			return "";
		} catch (IOException e) {
			return null;
		}
		return fileContent;
	}

	private Double readNumber() {
		Double value = Double.MIN_VALUE;
		String number = "";
		long pos = -1;
		try {
			pos = file.getFilePointer();
		} catch (IOException e) {
			return null;
		}
		String next = readNChars(1.0);
		if (!Pattern.matches("[0-9]", next)) {
			try {
				file.seek(pos);
			} catch (IOException e) {
				return null;
			}
			return null;
		}
		while (Pattern.matches("[0-9]", next)) {
			number += next;
			next = readNChars(1.0);
		}
		if (Pattern.matches("\\.", next)) {
			next = readNChars(1.0);
			number += ".";
			while (Pattern.matches("[0-9]", next)) {
				number += next;
				next = readNChars(1.0);
			}
		}

		pos = pos + number.length();
		try {
			file.seek(pos);
		} catch (IOException e) {
			return null;
		}

		value = Double.parseDouble(number);

		return value;
	}

	private String readNChars(double n) {
		int numOfChars = (int) Math.floor(n);
		String chars = "";
		for (int i = 0; i < numOfChars; i++) {
			try {
				int next = file.read();
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
			line = file.readLine();
		} catch (EOFException eof) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return line;
	}

}
