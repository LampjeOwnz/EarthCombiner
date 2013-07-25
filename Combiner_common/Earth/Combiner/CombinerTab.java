package Earth.Combiner;

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
		return CombinerCore.CombinerStone.itemID;
	}
	public String getTranslatedTabLabel()
	{
        return "CombinerTab";
	}
}
