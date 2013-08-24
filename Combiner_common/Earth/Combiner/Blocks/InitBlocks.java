package Earth.Combiner.Blocks;

import Earth.Combiner.CombinerCore;
import Earth.Combiner.Tabs.InitTabs;
import Earth.Combiner.lib.Ints;
import Earth.Combiner.lib.Strings;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class InitBlocks {

	// Blocks
	public final static Block CombineHeaterOff = (new CombMachine(Ints.COMBMACHINEOFF_ID, false)).setHardness(3.5F).setUnlocalizedName("combiner_front").setCreativeTab(InitTabs.CombinerTab);
	public final static	Block CombineHeaterOn = (new CombMachine(Ints.COMBMACHINEON_ID, true)).setHardness(3.5F).setLightValue(1.0F).setUnlocalizedName("combiner_front_lit");
	public final static Block CoalHeaterOff = (new CoalHeater(Ints.COALHEATEROFF_ID, false)).setHardness(3.5F).setUnlocalizedName("coalheater_front").setCreativeTab(InitTabs.CombinerTab);
	public final static	Block CoalHeaterOn = (new CoalHeater(Ints.COALHEATERON_ID, true)).setHardness(3.5F).setLightValue(1.0F).setUnlocalizedName("coalheater_front_lit");
	public final static Block StoneGlass = new StoneGlass(Ints.STONEGLASS_ID).setHardness(3.5F).setUnlocalizedName("StoneGlass");
	public final static Block StonePlank = new StoneWood(Ints.STONEPLANK_ID).setHardness(3.5F).setUnlocalizedName("StonePlank");
	
	public static void init(){

		// Blocks name and block register
		GameRegistry.registerBlock(CombineHeaterOff);
   	 	LanguageRegistry.addName(CombineHeaterOff, Strings.COMB_MACHINEOFF_NAME);
   	 	GameRegistry.registerBlock(CombineHeaterOn);	
   	 	LanguageRegistry.addName(CombineHeaterOn, Strings.COMB_MACHINEON_NAME);
   	 	GameRegistry.registerBlock(CoalHeaterOff);	
	 	LanguageRegistry.addName(CoalHeaterOff, Strings.COAL_HEATEROFF_NAME);
	 	GameRegistry.registerBlock(CoalHeaterOn);	
   	 	LanguageRegistry.addName(CoalHeaterOn, Strings.COAL_HEATERON_NAME);
   	 	GameRegistry.registerBlock(StoneGlass);	
	 	LanguageRegistry.addName(StoneGlass, Strings.STONEGLASS_NAME);
	 	GameRegistry.registerBlock(StonePlank);	
	 	LanguageRegistry.addName(StonePlank, Strings.STONEPLANK_NAME);
		
	}
}