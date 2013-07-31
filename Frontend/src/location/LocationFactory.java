package location;

import java_cup.runtime.Symbol;

public class LocationFactory {
	
	public static Location fromSymbol(Symbol symbol) {
		if(symbol != null) {
			return new DefaultLocation(symbol.left, symbol.right);
		} else {
			return new DefaultLocation(-1, -1);
		}
		
	}

}
