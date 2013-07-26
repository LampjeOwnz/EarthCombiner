package Earth.Combiner.Recipes;

import Earth.Combiner.ItemTools.InitItemTools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemToolRecipes {
	public static void init(){
		
		// Item recipe register
    	GameRegistry.addRecipe(new ItemStack(InitItemTools.RoundStone,1), new Object[] {
    		"xx ", "xx ", 'x', Block.cobblestone});
    	GameRegistry.addRecipe(new ItemStack(InitItemTools.CombinerStone,1), new Object[] {
            " x ", "x x", "sbs", 'x', Block.cobblestone, 's', Item.stick, 'b', InitItemTools.RoundStone});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.CombinerIron,1), new Object[] {
            " x ", "x x", "sbs", 'x', Item.ingotIron, 's', Item.stick, 'b', InitItemTools.RoundStone});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.CombinerGold,1), new Object[] {
            " x ", "x x", "sbs", 'x', Item.ingotGold, 's', Item.stick, 'b', InitItemTools.RoundStone});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.CombinerEmerald,1), new Object[] {
            " x ", "x x", "sbs", 'x', Item.diamond, 's', Item.stick, 'b', InitItemTools.RoundStone});
   	 	
   	 	GameRegistry.addRecipe(new ItemStack(Block.torchWood,8), new Object[] {
   	 		"xs ", " b ", 'x', Block.planks, 's', Block.wood, 'b', InitItemTools.CombinerStone});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.WoodScrap,4), new Object[] {
	 		"s  ", "b  ", 's', Block.wood, 'b', InitItemTools.CombinerStone});
   	 	
   	 	
   	 	// Tool recipe register
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.ToolStone,1), new Object[] {
		 "bvx", " d ", 'x', Item.axeStone, 'v', Item.pickaxeStone, 'b', Item.shovelStone, 'd', InitItemTools.CombinerStone});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.ToolIron,1), new Object[] {
		 "bvx", " d ", 'x', Item.axeIron, 'v', Item.pickaxeIron, 'b', Item.shovelIron, 'd', InitItemTools.CombinerIron});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.ToolGold,1), new Object[] {
		 "bvx", " d ", 'x', Item.axeGold, 'v', Item.pickaxeGold, 'b', Item.shovelGold, 'd', InitItemTools.CombinerGold});
   	 	GameRegistry.addRecipe(new ItemStack(InitItemTools.ToolEmerald,1), new Object[] {
		 "bvx", " d ", 'x', Item.axeDiamond, 'v', Item.pickaxeDiamond, 'b', Item.shovelDiamond, 'd', InitItemTools.CombinerEmerald});
	}

}
