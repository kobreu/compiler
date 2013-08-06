package edu.tum.juna.stdlib.math;

import java.util.Arrays;
import java.util.List;

import edu.tum.juna.Preconditions;
import edu.tum.juna.types.LuaFunctionNative;
import edu.tum.juna.types.LuaType;

public class Modf extends LuaFunctionNative {

	LuaType[][] expectedTypes = { { LuaType.NUMBER } };

	@Override
	public List<Object> apply(List<Object> arguments) {

		Preconditions.checkArguments("modf", arguments, expectedTypes);
		double x = (double) arguments.get(0);

		String mediator = Double.valueOf(x).toString();
		double integral = Double.valueOf(mediator.substring(0, mediator.indexOf('.')));
		double fractional = Double.valueOf(mediator.substring(mediator.indexOf('.')));

		return Arrays.asList((Object) integral, (Object) fractional);
	}
}
