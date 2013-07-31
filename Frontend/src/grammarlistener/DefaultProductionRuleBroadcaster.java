package grammarlistener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DefaultProductionRuleBroadcaster extends Observable {

	List<ProductionRuleListener> productionRuleListeners = new ArrayList<>();
	
	public void reportProductionRule(ProductionRule rule) {
		for(ProductionRuleListener listener : productionRuleListeners) {
			listener.appliedRule(rule);
		}
	}

}
