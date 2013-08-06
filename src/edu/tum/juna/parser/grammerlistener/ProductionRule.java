package edu.tum.juna.parser.grammerlistener;

import edu.tum.juna.ast.SyntaxNode;
import java_cup.runtime.Symbol;

public interface ProductionRule {
	
	ProductionRuleType getType();
	
	SyntaxNode getResultingNode();
	
	SyntaxNode[] getReducedNodes();
	
	Symbol[] getSymbols();

}
