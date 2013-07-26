package Earth.Combiner.Blocks;


import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Earth.Combiner.CombinerCore;
import Earth.Combiner.Tabs.InitTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class StoneGlass extends Block
{
    public StoneGlass(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(InitTabs.CombinerTab);
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(CombinerCore.modID + ":" + (this.getUnlocalizedName().substring(5)));
	}
    
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
}

