package Earth.Combiner.Recipes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import Earth.Combiner.CombinerCore;
import Earth.Combiner.Blocks.InitBlocks;
import Earth.Combiner.ItemTools.InitItemTools;
import Earth.Combiner.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRecipes {
	public static void init(){

		// Blocks recipe register
	 	GameRegistry.addRecipe(new ItemStack(InitBlocks.CombineHeaterOff,1), new Object[] {
	 		"xxx", "xdx", "xxx", 'x', Block.cobblestone, 'd', InitItemTools.CombinerStone});
		
		
	}
}
