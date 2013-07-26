package Earth.Combiner;

import Earth.Combiner.ItemTools.InitItemTools;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
 
public class CombinerTab extends CreativeTabs {
 
	public CombinerTab(int position, String tabID) {
		super(position, tabID);
	}
 
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return InitItemTools.CombinerStone.itemID;
	}
	public String getTranslatedTabLabel()
	{
        return "CombinerTab";
	}
}
