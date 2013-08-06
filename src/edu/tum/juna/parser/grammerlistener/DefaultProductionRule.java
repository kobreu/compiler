package edu.tum.juna.parser.grammerlistener;

import edu.tum.juna.ast.SyntaxNode;
import java_cup.runtime.Symbol;

public class DefaultProductionRule implements ProductionRule {

	private ProductionRuleType type;
	private SyntaxNode[] syntaxNodes;
	private Symbol[] symbols;
	private SyntaxNode resultingNode;

	public DefaultProductionRule(ProductionRuleType type, SyntaxNode resultingNode, SyntaxNode[] syntaxNodes, Symbol[] symbols) {
		this.type = type;
		this.resultingNode = resultingNode;
		this.syntaxNodes = syntaxNodes;
		this.symbols = symbols;
		
	}
	
	@Override
	public ProductionRuleType getType() {
		return type;
	}

	@Override
	public SyntaxNode[] getReducedNodes() {
		return syntaxNodes;
	}

	@Override
	public Symbol[] getSymbols() {
		return symbols;
	}

	@Override
	public SyntaxNode getResultingNode() {
		return resultingNode;
	}

}
