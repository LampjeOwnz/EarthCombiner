package Earth.Combiner.ItemTools;

import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import Earth.Combiner.CombinerCore;
import Earth.Combiner.lib.CombinerTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
 
public class CombinerItem extends Item{
       
        public CombinerItem(int par1){
                super(par1);
                maxStackSize=64;
                this.setCreativeTab(CombinerTabs.CombinerTab);
        }
        
        @Override
    	@SideOnly(Side.CLIENT)
    	public void registerIcons(IconRegister iconRegister) {
    		this.itemIcon = iconRegister.registerIcon(CombinerCore.modID + ":" + (this.getUnlocalizedName().substring(5)));
    	}
}
