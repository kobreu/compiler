package grammarlistener;

import java.util.Observable;

public interface ProductionRuleBroadcaster extends Obser {
	
	// internal public void reportProductionRule(ProductionRule rule);
	
	public void registerListener(ProductionRuleListener listener);

}
