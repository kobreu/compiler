package grammarlistener;

import edu.tum.lua.ast.SyntaxNode;
import java_cup.runtime.Symbol;

public interface ProductionRule {
	
	ProductionRuleType getType();
	
	SyntaxNode getResultingNode();
	
	SyntaxNode[] getReducedNodes();
	
	Symbol[] getSymbols();

}
