package Earth.Combiner.ItemTools;

import Earth.Combiner.lib.Strings;
import Earth.Combiner.lib.NewMaterials;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class InitItemTools {

	// Items
    public final static Item RoundStone = new CombinerItem(17070).setUnlocalizedName("RoundStone");
    public final static Item CombinerStone = new CombinerItem(17071).setUnlocalizedName("CombinerStone");
    public final static Item CombinerIron = new CombinerItem(17072).setUnlocalizedName("CombinerIron");
    public final static Item CombinerGold = new CombinerItem(17073).setUnlocalizedName("CombinerGold");
    public final static Item CombinerEmerald = new CombinerItem(17074).setUnlocalizedName("CombinerEmerald");
    public final static Item WoodScrap = new CombinerItem(17075).setUnlocalizedName("WoodScrap");
    public final static Item CobbleScrap = new CombinerItem(17076).setUnlocalizedName("CobbleScrap");
    
    // Tools
    public final static Item ToolStone = new CombTool(17180, NewMaterials.NewSTONE).setUnlocalizedName("ToolStone");
    public final static Item ToolIron = new CombTool(17181, NewMaterials.NewIRON).setUnlocalizedName("ToolIron");
    public final static Item ToolGold = new CombTool(17182, NewMaterials.NewGOLD).setUnlocalizedName("ToolGold");
    public final static Item ToolEmerald = new CombTool(17183, NewMaterials.NewEMERALD).setUnlocalizedName("ToolEmerald");
    
    public static void init(){

    	// Item name register
    	LanguageRegistry.addName(RoundStone, Strings.ROUNDSTONE_NAME);
    	LanguageRegistry.addName(CombinerStone, Strings.COMBSTONE_NAME);
    	LanguageRegistry.addName(CombinerIron, Strings.COMBIRON_NAME);
    	LanguageRegistry.addName(CombinerGold, Strings.COMBGOLD_NAME);
    	LanguageRegistry.addName(CombinerEmerald, Strings.COMBEMERALD_NAME);
    	LanguageRegistry.addName(WoodScrap, Strings.WOODSCRAP_NAME);
   	 	LanguageRegistry.addName(CobbleScrap, Strings.COBBLESCRAP_NAME);
   	 	
   	 	// Tool name register
    	LanguageRegistry.addName(ToolStone, Strings.COBBLETOOL_NAME);
   	 	LanguageRegistry.addName(ToolIron, Strings.IRONTOOL_NAME);
   	 	LanguageRegistry.addName(ToolGold, Strings.GOLDTOOL_NAME);
   	 	LanguageRegistry.addName(ToolEmerald, Strings.EMERALDTOOL_NAME);
		
	}
}
