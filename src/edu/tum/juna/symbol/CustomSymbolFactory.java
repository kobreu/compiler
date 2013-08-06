package edu.tum.juna.symbol;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import edu.tum.juna.ast.SyntaxNode;
import edu.tum.juna.parser.location.LocationFactory;

public class CustomSymbolFactory extends ComplexSymbolFactory {

	@Override
	public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
		if (value instanceof SyntaxNode) {
			((SyntaxNode) value).setStart(LocationFactory.fromSymbol(left));
			if (right.left != -1 && right.right != -1)
				((SyntaxNode) value).setEnd(LocationFactory.fromSymbol(right));
		}

		Symbol s = new Symbol(id, left, right, value);
		return s;
	}

	@Override
	public Symbol newSymbol(String name, int id) {
		return super.newSymbol(name, id);
	}

	@Override
	public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
		return super.newSymbol(name, id, left, right);
	}

}
