package Earth.Combiner.ItemTools;

import Earth.Combiner.lib.Ints;
import Earth.Combiner.lib.Strings;
import Earth.Combiner.lib.NewMaterials;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class InitItemTools {

	// Items
    public final static Item RoundStone =  new CombinerItem(Ints.ROUNDSTONE_ID).setUnlocalizedName("RoundStone");
    public final static Item CombinerStone = new CombinerItem(Ints.COMBSTONE_ID).setUnlocalizedName("CombinerStone");
    public final static Item CombinerIron = new CombinerItem(Ints.COMBIRON_ID).setUnlocalizedName("CombinerIron");
    public final static Item CombinerGold = new CombinerItem(Ints.COMBGOLD_ID).setUnlocalizedName("CombinerGold");
    public final static Item CombinerEmerald = new CombinerItem(Ints.COMBEMERALD_ID).setUnlocalizedName("CombinerEmerald");
    public final static Item WoodScrap = new CombinerItem(Ints.WOODSCRAP_ID).setUnlocalizedName("WoodScrap");
    public final static Item CobbleScrap = new CombinerItem(Ints.COBBLESCRAP_ID).setUnlocalizedName("CobbleScrap");
    public final static Item CompWoodScrap = new CombinerItem(Ints.COMPWOODSCRAP_ID).setUnlocalizedName("CompressedWoodScrap");
    public final static Item CompCoalScrap = new CombinerItem(Ints.COMPCOALSCRAP_ID).setUnlocalizedName("CompressedCoalScrap");
    
    // Tools
    public final static Item ToolStone = new CombTool(Ints.TOOLSTONE_ID, NewMaterials.NewSTONE).setUnlocalizedName("ToolStone");
    public final static Item ToolIron = new CombTool(Ints.TOOLIRON_ID, NewMaterials.NewIRON).setUnlocalizedName("ToolIron");
    public final static Item ToolGold = new CombTool(Ints.TOOLGOLD_ID, NewMaterials.NewGOLD).setUnlocalizedName("ToolGold");
    public final static Item ToolEmerald = new CombTool(Ints.TOOLEMERALD_ID, NewMaterials.NewEMERALD).setUnlocalizedName("ToolEmerald");
    
    public static void init(){

    	// Item name register
    	LanguageRegistry.addName(RoundStone, Strings.ROUNDSTONE_NAME);
    	LanguageRegistry.addName(CombinerStone, Strings.COMBSTONE_NAME);
    	LanguageRegistry.addName(CombinerIron, Strings.COMBIRON_NAME);
    	LanguageRegistry.addName(CombinerGold, Strings.COMBGOLD_NAME);
    	LanguageRegistry.addName(CombinerEmerald, Strings.COMBEMERALD_NAME);
    	LanguageRegistry.addName(WoodScrap, Strings.WOODSCRAP_NAME);
   	 	LanguageRegistry.addName(CobbleScrap, Strings.COBBLESCRAP_NAME);
   	 	LanguageRegistry.addName(CompWoodScrap, Strings.COMPWOODSCRAP_NAME);
   	 	LanguageRegistry.addName(CompCoalScrap, Strings.COMPCOALSCRAP_NAME);
   	 	
   	 	// Tool name register
    	LanguageRegistry.addName(ToolStone, Strings.COBBLETOOL_NAME);
   	 	LanguageRegistry.addName(ToolIron, Strings.IRONTOOL_NAME);
   	 	LanguageRegistry.addName(ToolGold, Strings.GOLDTOOL_NAME);
   	 	LanguageRegistry.addName(ToolEmerald, Strings.EMERALDTOOL_NAME);
		
	}
}
