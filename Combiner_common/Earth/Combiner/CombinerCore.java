package Earth.Combiner;

import Earth.Combiner.Blocks.CombMachine;
import Earth.Combiner.Blocks.InitBlocks;
import Earth.Combiner.Blocks.StoneGlass;
import Earth.Combiner.Blocks.StoneWood;
import Earth.Combiner.ItemTools.CombTool;
import Earth.Combiner.ItemTools.CombinerItem;
import Earth.Combiner.ItemTools.InitItemTools;
import Earth.Combiner.core.proxy.CommonProxy;
import Earth.Combiner.lib.NewMaterials;
import Earth.Combiner.lib.References;
import Earth.Combiner.MachineUtilety.GuiHandler;
import Earth.Combiner.MachineUtilety.TileEntityCombMachine;
import Earth.Combiner.MachineUtilety.CombMachinepacketHandler;
import Earth.Combiner.Recipes.BlockRecipes;
import Earth.Combiner.Recipes.ItemToolRecipes;
import Earth.Combiner.Tabs.InitTabs;
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
          	                      

        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	InitTabs.init();
        	
        	NewMaterials.init();
        	
        	InitItemTools.init();
        	
        	ItemToolRecipes.init();
        	
        	InitBlocks.init();
        	
        	BlockRecipes.init();
        	
       	 	// Blocks mechanics register
            GameRegistry.registerTileEntity(TileEntityCombMachine.class, "tileentitycombmachine");
            NetworkRegistry.instance().registerGuiHandler(this, guihandler);
        }
        
               
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
        }
        
              
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {

        }
}


