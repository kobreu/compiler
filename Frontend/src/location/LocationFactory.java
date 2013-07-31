package location;

import java_cup.runtime.Symbol;

public class LocationFactory {
	
	public static Location fromSymbol(Symbol symbol) {
		return new DefaultLocation(symbol.left, symbol.right);
	}

}
