package grammarlistener;

import java.util.ArrayList;
import java.util.List;

public class DefaultProductionRuleBroadcaster implements ProductionRuleBroadcaster {

	List<ProductionRuleListener> productionRuleListeners = new ArrayList<>();
	
	public void reportProductionRule(ProductionRule rule) {
		for(ProductionRuleListener listener : productionRuleListeners) {
			listener.appliedRule(rule);
		}
	}

	@Override
	public void registerListener(ProductionRuleListener listener) {
		productionRuleListeners.add(listener);
	}

}
