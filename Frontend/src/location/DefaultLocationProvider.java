package location;

import java.util.HashMap;
import java.util.Map;

import edu.tum.lua.ast.SyntaxNode;

public class DefaultLocationProvider implements LocationTable {

	private Map<SyntaxNode, Location> locationMap = new HashMap<>();

	@Override
	public Location getLocation(SyntaxNode syntaxNode) {
		return locationMap.get(syntaxNode);
	}
	
	private static DefaultLocationProvider instance;
	
	public void addLocation(SyntaxNode syntaxNode, Location location) {
		locationMap.put(syntaxNode, location);
	}
	
	public static DefaultLocationProvider instance() {
		if(instance == null) {
			instance = new DefaultLocationProvider();
		}
		return instance;
	}
	
	
	

}
