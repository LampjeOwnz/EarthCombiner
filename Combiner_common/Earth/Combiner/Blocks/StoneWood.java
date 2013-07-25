package Earth.Combiner.Blocks;


import Earth.Combiner.CombinerCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class StoneWood extends Block
{
    public StoneWood(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(CombinerCore.CombinerTab);
    }
    @Override
   	@SideOnly(Side.CLIENT)
   	public void registerIcons(IconRegister iconRegister) {
   		this.blockIcon = iconRegister.registerIcon(CombinerCore.modID + ":" + (this.getUnlocalizedName().substring(5)));
   	}
}


