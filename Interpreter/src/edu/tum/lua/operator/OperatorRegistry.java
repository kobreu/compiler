package edu.tum.lua.operator;

import edu.tum.lua.operator.arithmetic.AddOperator;
import edu.tum.lua.operator.arithmetic.DivOperator;
import edu.tum.lua.operator.arithmetic.ModOperator;
import edu.tum.lua.operator.arithmetic.MulOperator;
import edu.tum.lua.operator.arithmetic.PowOperator;
import edu.tum.lua.operator.arithmetic.SubOperator;
import edu.tum.lua.operator.arithmetic.UnmOperator;
import edu.tum.lua.operator.list.ConcatOperator;
import edu.tum.lua.operator.list.LengthOperator;
import edu.tum.lua.operator.logical.AndOperator;
import edu.tum.lua.operator.logical.NotOperator;
import edu.tum.lua.operator.logical.OrOperator;
import edu.tum.lua.operator.relational.EqOperator;
import edu.tum.lua.operator.relational.GEOperator;
import edu.tum.lua.operator.relational.GTOperator;
import edu.tum.lua.operator.relational.LEOperator;
import edu.tum.lua.operator.relational.LTOperator;
import edu.tum.lua.operator.relational.NotEqOperator;

public class OperatorRegistry {

	public static final Operator[] registry = { new AddOperator(), new SubOperator(), new MulOperator(),
			new DivOperator(), new PowOperator(), new ModOperator(), new ConcatOperator(), new LTOperator(),
			new LEOperator(), new GTOperator(), new GEOperator(), new EqOperator(), new NotEqOperator(),
			new AndOperator(), new OrOperator(), new UnmOperator(), new NotOperator(), new LengthOperator() };

}
