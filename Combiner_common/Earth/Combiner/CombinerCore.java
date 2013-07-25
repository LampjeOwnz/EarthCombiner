package Earth.Combiner;

import Earth.Combiner.Blocks.CombMachine;
import Earth.Combiner.Blocks.StoneGlass;
import Earth.Combiner.Blocks.StoneWood;
import Earth.Combiner.ItemTools.CombTool;
import Earth.Combiner.ItemTools.CombinerItem;
import Earth.Combiner.core.proxy.CommonProxy;
import Earth.Combiner.lib.References;
import Earth.Combiner.MachineUtilety.GuiHandler;
import Earth.Combiner.MachineUtilety.TileEntityCombMachine;
import Earth.Combiner.MachineUtilety.CombMachinepacketHandler;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit; 
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(	
		modid 		= References.MODID, 
		name 		= References.MODNAME, 
		version 	= References.VERSION
)
@NetworkMod(
	clientSideRequired	= true, 
	serverSideRequired	= false, 
	channels 			= "CombinerCore", 
	packetHandler 		= CombMachinepacketHandler.class
)
public class CombinerCore {
	
		// Says where the client and server 'proxy' code is loaded.
    	@SidedProxy(
    			clientSide = References.CLIENT_PROXY_LOCATION, 
    			serverSide = References.COMMON_PROXY_LOCATION
    	)
    	
    	public static CommonProxy proxy;
    	
    	
        // The instance of your mod that Forge uses.
        @Instance
    	public static CombinerCore instance = new  CombinerCore();
    	private GuiHandler guihandler = new GuiHandler();
        
        
        // Needed for the textures
        public static final String modID = "combinercore";
        
        
        // Creative tabs
     	public static CreativeTabs CombinerTab = new CombinerTab(CreativeTabs.getNextID(),"CombinerTab");
     	
     	
     	// Tools material + strength
    	public static EnumToolMaterial NewSTONE= EnumHelper.addToolMaterial("NewSTONE", 1, 262, 6F, 1, 10);
    	public static EnumToolMaterial NewIRON= EnumHelper.addToolMaterial("NewIRON", 2, 500, 8F, 2, 18);
    	public static EnumToolMaterial NewGOLD= EnumHelper.addToolMaterial("NewGOLD", 0, 64, 14F, 0, 24);
    	public static EnumToolMaterial NewEMERALD= EnumHelper.addToolMaterial("NewEMERALD", 3, 3122, 11F, 3, 20);
        
     	
     	// Items
        public final static Item RoundStone = new CombinerItem(17070).setUnlocalizedName("RoundStone");
        public final static Item CombinerStone = new CombinerItem(17071).setUnlocalizedName("CombinerStone");
        public final static Item CombinerIron = new CombinerItem(17072).setUnlocalizedName("CombinerIron");
        public final static Item CombinerGold = new CombinerItem(17073).setUnlocalizedName("CombinerGold");
        public final static Item CombinerEmerald = new CombinerItem(17074).setUnlocalizedName("CombinerEmerald");
        public final static Item WoodScrap = new CombinerItem(17075).setUnlocalizedName("WoodScrap");
        public final static Item CobbleScrap = new CombinerItem(17076).setUnlocalizedName("CobbleScrap");
        
        
        // Tools
        public final static Item ToolStone = new CombTool(17180, NewSTONE).setUnlocalizedName("ToolStone");
        public final static Item ToolIron = new CombTool(17181, NewIRON).setUnlocalizedName("ToolIron");
        public final static Item ToolGold = new CombTool(17182, NewGOLD).setUnlocalizedName("ToolGold");
        public final static Item ToolEmerald = new CombTool(17183, NewEMERALD).setUnlocalizedName("ToolEmerald");
        
        
        // Blocks
        public final static Block CombineHeaterOff = (new CombMachine(2356, false)).setHardness(3.5F).setUnlocalizedName("combiner_front").setCreativeTab(CombinerCore.CombinerTab);
   	 	public final static	Block CombineHeaterOn = (new CombMachine(2357, true)).setHardness(3.5F).setLightValue(1.0F).setUnlocalizedName("combiner_front_lit");
   	 	public final static Block StoneGlass = new StoneGlass(2358).setHardness(3.5F).setUnlocalizedName("StoneGlass");
   	 	public final static Block StonePlank = new StoneWood(2359).setHardness(3.5F).setUnlocalizedName("StonePlank");
        
        
        
        // Pre Init
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	// Item name register
        	LanguageRegistry.addName(RoundStone, "Stone Round");
        	LanguageRegistry.addName(CombinerStone, "Combiner Stone");
        	LanguageRegistry.addName(CombinerIron, "Combiner Iron");
        	LanguageRegistry.addName(CombinerGold, "Combiner Gold");
        	LanguageRegistry.addName(CombinerEmerald, "Combiner Diamond");
        	LanguageRegistry.addName(WoodScrap, "Wood Scrap");
       	 	LanguageRegistry.addName(CobbleScrap, "Cobble Scrap");
        	       	          
        	// Item recipe register
        	GameRegistry.addRecipe(new ItemStack(RoundStone,1), new Object[] {
        		"xx ", "xx ", 'x', Block.cobblestone});
        	GameRegistry.addRecipe(new ItemStack(CombinerStone,1), new Object[] {
                " x ", "x x", "sbs", 'x', Block.cobblestone, 's', Item.stick, 'b', RoundStone});
       	 	GameRegistry.addRecipe(new ItemStack(CombinerIron,1), new Object[] {
                " x ", "x x", "sbs", 'x', Item.ingotIron, 's', Item.stick, 'b', RoundStone});
       	 	GameRegistry.addRecipe(new ItemStack(CombinerGold,1), new Object[] {
                " x ", "x x", "sbs", 'x', Item.ingotGold, 's', Item.stick, 'b', RoundStone});
       	 	GameRegistry.addRecipe(new ItemStack(CombinerEmerald,1), new Object[] {
                " x ", "x x", "sbs", 'x', Item.diamond, 's', Item.stick, 'b', RoundStone});
       	 	GameRegistry.addRecipe(new ItemStack(Block.torchWood,8), new Object[] {
       	 		"xs", " b", 'x', Block.planks, 's', Block.wood, 'b', CombinerStone});
       	 	
        	
       	 	// Tool name register
        	LanguageRegistry.addName(ToolStone, "Cobble Tool");
       	 	LanguageRegistry.addName(ToolIron, "Iron Tool");
       	 	LanguageRegistry.addName(ToolGold, "Gold Tool");
       	 	LanguageRegistry.addName(ToolEmerald, "Diamond Tool");
       	 	
       	 	// Tool recipe register
       	 	GameRegistry.addRecipe(new ItemStack(ToolStone,1), new Object[] {
    		 "bvx", " d ", 'x', Item.axeStone, 'v', Item.pickaxeStone, 'b', Item.shovelStone, 'd', CombinerStone});
       	 	GameRegistry.addRecipe(new ItemStack(ToolIron,1), new Object[] {
    		 "bvx", " d ", 'x', Item.axeIron, 'v', Item.pickaxeIron, 'b', Item.shovelIron, 'd', CombinerIron});
       	 	GameRegistry.addRecipe(new ItemStack(ToolGold,1), new Object[] {
    		 "bvx", " d ", 'x', Item.axeGold, 'v', Item.pickaxeGold, 'b', Item.shovelGold, 'd', CombinerGold});
       	 	GameRegistry.addRecipe(new ItemStack(ToolEmerald,1), new Object[] {
    		 "bvx", " d ", 'x', Item.axeDiamond, 'v', Item.pickaxeDiamond, 'b', Item.shovelDiamond, 'd', CombinerEmerald});
       	 	
       	 	
       	 	// Blocks name and block register
       	 	GameRegistry.registerBlock(CombineHeaterOff);
       	 	LanguageRegistry.addName(CombineHeaterOff, "CombinerMachine");
       	 	GameRegistry.registerBlock(CombineHeaterOn);	
       	 	LanguageRegistry.addName(CombineHeaterOn, "CombMachine");
       	 	GameRegistry.registerBlock(StoneGlass);	
    	 	LanguageRegistry.addName(StoneGlass, "Stone Glass");
    	 	GameRegistry.registerBlock(StonePlank);	
    	 	LanguageRegistry.addName(StonePlank, "Stone Plank");
    	 	
    	 	// Blocks recipe register
    	 	GameRegistry.addRecipe(new ItemStack(CombineHeaterOff,1), new Object[] {
       		 "xxx", "xdx", "xxx", 'x', Block.cobblestone, 'd', CombinerIron});
       	 	
       	 	// Blocks mechanics register
            GameRegistry.registerTileEntity(TileEntityCombMachine.class, "tileentitycombmachine");
            NetworkRegistry.instance().registerGuiHandler(this, guihandler);
        }
        
        
        // Init        
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
        }
        
        
        // Post Init        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}


