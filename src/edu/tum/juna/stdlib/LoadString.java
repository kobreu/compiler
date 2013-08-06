package edu.tum.juna.stdlib;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.tum.juna.LocalEnvironment;
import edu.tum.juna.Preconditions;
import edu.tum.juna.ast.Block;
import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.types.LuaFunctionInterpreted;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class LoadString extends LuaFunctionNative {

	private static final LuaType[][] expectedTypes = { { LuaType.STRING } };

	@Override
	public List<Object> apply(List<Object> arguments) {
		Preconditions.checkArguments("loadstring", arguments, expectedTypes);
		try {
			Block block = ParserUtil.loadString((String) arguments.get(0));
			LuaFunctionInterpreted function = new LuaFunctionInterpreted(new LinkedList<String>(), false, block,
					new LocalEnvironment());
			return Collections.singletonList((Object) function);
		} catch (Exception e) {
			return Collections.singletonList(null);
		}
	}

}
