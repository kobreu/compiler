package grammarlistener;

import java.util.Observer;


public interface ProductionRuleBroadcaster {
	
	public void registerListener(ProductionRuleListener listener);

}
