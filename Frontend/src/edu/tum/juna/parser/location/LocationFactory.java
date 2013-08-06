package edu.tum.juna.parser.location;

import java_cup.runtime.Symbol;

public class LocationFactory {

	public static Location fromSymbol(Symbol symbol) {
		if (symbol != null) {
			return new DefaultLocation(symbol.left, symbol.right);
		}

		return new DefaultLocation(-1, -1);
	}

}
