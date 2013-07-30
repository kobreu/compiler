package edu.tum.lua.stdlib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.exceptions.LuaRuntimeException;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaType;

public class LoadFile extends LuaFunctionNative {

	@Override
	public List<Object> apply(List<Object> arguments) {
		InputStream input = System.in;
		if (arguments.size() > 0) {
			if (LuaType.getTypeOf(arguments.get(0)) != LuaType.STRING)
				throw new LuaBadArgumentException(1, "loadfile", "string", LuaType.getTypeOf(arguments.get(0))
						.toString());
			try {
				input = new FileInputStream(arguments.get(0).toString());
			} catch (FileNotFoundException e) {
				throw new LuaRuntimeException("File not found");
			}
		}
		String result = "";
		try {

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader inputReader = new BufferedReader(isr);
			String line = inputReader.readLine();
			while (line != null) {
				result = result.concat(line);
				line = inputReader.readLine();
			}
			inputReader.close();
		} catch (IOException e) {
		}
		LoadString l = new LoadString();
		return l.apply(result);
	}

}
