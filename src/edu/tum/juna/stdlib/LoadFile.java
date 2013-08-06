package edu.tum.juna.stdlib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import edu.tum.juna.exceptions.LuaBadArgumentException;
import edu.tum.juna.exceptions.LuaIOException;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class LoadFile extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		InputStream input = System.in;

		if (arguments.size() > 0) {
			if (LuaType.getTypeOf(arguments.get(0)) != LuaType.STRING) {
				throw new LuaBadArgumentException(1, "loadfile", "string", LuaType.getTypeOf(arguments.get(0))
						.toString());
			}
			try {
				input = new FileInputStream(arguments.get(0).toString());
			} catch (FileNotFoundException e) {
				throw new LuaIOException("File not found");
			}
		}

		String result = "";

		try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(input))) {
			String line = inputReader.readLine();
			while (line != null) {
				result = result.concat(line);
				result = result.concat("\n");
				line = inputReader.readLine();
			}
			inputReader.close();
		} catch (IOException e) {
		}

		return new LoadString().apply(result);
	}
}
