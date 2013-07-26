package Earth.Combiner.lib;

import net.minecraft.creativetab.CreativeTabs;
import Earth.Combiner.CombinerTab;

public class CombinerTabs {
	
	// New Creative tabs
	public static CreativeTabs CombinerTab;
	
	public static void init(){
		
		// Tab names
		CombinerTab = new CombinerTab(CreativeTabs.getNextID(),"CombinerTab");
	}
}
