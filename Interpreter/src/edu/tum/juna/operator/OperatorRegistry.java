package edu.tum.juna.operator;

import edu.tum.juna.operator.arithmetic.AddOperator;
import edu.tum.juna.operator.arithmetic.DivOperator;
import edu.tum.juna.operator.arithmetic.ModOperator;
import edu.tum.juna.operator.arithmetic.MulOperator;
import edu.tum.juna.operator.arithmetic.PowOperator;
import edu.tum.juna.operator.arithmetic.SubOperator;
import edu.tum.juna.operator.arithmetic.UnmOperator;
import edu.tum.juna.operator.list.ConcatOperator;
import edu.tum.juna.operator.list.LengthOperator;
import edu.tum.juna.operator.logical.AndOperator;
import edu.tum.juna.operator.logical.NotOperator;
import edu.tum.juna.operator.logical.OrOperator;
import edu.tum.juna.operator.relational.EqOperator;
import edu.tum.juna.operator.relational.GEOperator;
import edu.tum.juna.operator.relational.GTOperator;
import edu.tum.juna.operator.relational.LEOperator;
import edu.tum.juna.operator.relational.LTOperator;
import edu.tum.juna.operator.relational.NotEqOperator;

public class OperatorRegistry {

	public static final Operator[] registry = { new AddOperator(), new SubOperator(), new MulOperator(),
			new DivOperator(), new PowOperator(), new ModOperator(), new ConcatOperator(), new LTOperator(),
			new LEOperator(), new GTOperator(), new GEOperator(), new EqOperator(), new NotEqOperator(),
			new AndOperator(), new OrOperator(), new UnmOperator(), new NotOperator(), new LengthOperator() };

}
