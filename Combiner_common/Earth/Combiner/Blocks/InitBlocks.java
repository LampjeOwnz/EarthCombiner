package Earth.Combiner.Blocks;

import Earth.Combiner.CombinerCore;
import Earth.Combiner.MachineUtilety.GuiHandler;
import Earth.Combiner.MachineUtilety.TileEntityCombMachine;
import Earth.Combiner.lib.CombinerTabs;
import Earth.Combiner.lib.Strings;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class InitBlocks {

	// Blocks
	public final static Block CombineHeaterOff = (new CombMachine(2356, false)).setHardness(3.5F).setUnlocalizedName("combiner_front").setCreativeTab(CombinerTabs.CombinerTab);
	public final static	Block CombineHeaterOn = (new CombMachine(2357, true)).setHardness(3.5F).setLightValue(1.0F).setUnlocalizedName("combiner_front_lit");
	public final static Block StoneGlass = new StoneGlass(2358).setHardness(3.5F).setUnlocalizedName("StoneGlass");
	public final static Block StonePlank = new StoneWood(2359).setHardness(3.5F).setUnlocalizedName("StonePlank");
	
	public static void init(){

		// Blocks name and block register
		GameRegistry.registerBlock(CombineHeaterOff);
   	 	LanguageRegistry.addName(CombineHeaterOff, Strings.COMB_MACHINEOFF_NAME);
   	 	GameRegistry.registerBlock(CombineHeaterOn);	
   	 	LanguageRegistry.addName(CombineHeaterOn, Strings.COMB_MACHINEON_NAME);
   	 	GameRegistry.registerBlock(StoneGlass);	
	 	LanguageRegistry.addName(StoneGlass, Strings.STONEGLASS_NAME);
	 	GameRegistry.registerBlock(StonePlank);	
	 	LanguageRegistry.addName(StonePlank, Strings.STONEPLANK_NAME);
		
	}
}