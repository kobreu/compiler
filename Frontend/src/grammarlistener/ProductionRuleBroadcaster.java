package grammarlistener;


public interface ProductionRuleBroadcaster {
	
	// internal public void reportProductionRule(ProductionRule rule);
	
	public void registerListener(ProductionRuleListener listener);

}
