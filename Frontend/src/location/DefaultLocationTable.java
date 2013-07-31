package location;

import java.util.HashMap;
import java.util.Map;

import edu.tum.lua.ast.SyntaxNode;
import grammarlistener.ProductionRule;
import grammarlistener.ProductionRuleListener;

public class DefaultLocationTable implements LocationTable, ProductionRuleListener {
	
	private Map<SyntaxNode, Location> locationMap = new HashMap<>();

	@Override
	public Location getLocation(SyntaxNode syntaxNode) {
		return locationMap.get(syntaxNode);
	}
	
	public void addLocation(SyntaxNode syntaxNode, Location location) {
		locationMap.put(syntaxNode, location);
	}

	@Override
	public void appliedRule(ProductionRule rule) {
		System.out.println("applied rule!");
		
	}
	
	
	

}
