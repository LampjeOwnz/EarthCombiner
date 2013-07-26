package Earth.Combiner.Tabs;

import net.minecraft.creativetab.CreativeTabs;
import Earth.Combiner.Tabs.CombinerTab;

public class InitTabs {
	
	// New Creative tabs
	public static CreativeTabs CombinerTab;
	
	public static void init(){
		
		// Tab names
		CombinerTab = new CombinerTab(CreativeTabs.getNextID(),"CombinerTab");
	}
}
