package location;

import edu.tum.lua.ast.SyntaxNode;

public interface LocationTable {

	public Location getLocation(SyntaxNode symbol);

}
