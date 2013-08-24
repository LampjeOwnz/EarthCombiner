package Earth.Combiner;

import java.io.File;

import Earth.Combiner.Blocks.InitBlocks;
import Earth.Combiner.Configuration.ConfigurationHandler;
import Earth.Combiner.ItemTools.InitItemTools;
import Earth.Combiner.core.proxy.CommonProxy;
import Earth.Combiner.lib.Ints;
import Earth.Combiner.lib.Mechanics;
import Earth.Combiner.lib.NewMaterials;
import Earth.Combiner.lib.References;
import Earth.Combiner.core.handlers.PacketHandler;
//import Earth.Combiner.core.packets.EarthpacketsHandler;
import Earth.Combiner.Recipes.BlockRecipes;
import Earth.Combiner.Recipes.ItemToolRecipes;
import Earth.Combiner.Tabs.InitTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;


@Mod(	
		modid 		= References.MODID, 
		name 		= References.MODNAME, 
		version 	= References.VERSION
)

@NetworkMod(
	clientSideRequired	= true, 
	serverSideRequired	= false, 
	channels 			= {References.CHANNEL}, 
	packetHandler 		= PacketHandler.class
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
          	                      

        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath()+ File.separator + References.MODID + File.separator + References.MODID + ".cfg"));
        	
        	Mechanics.init();
        	
        	InitTabs.init();
        	
        	NewMaterials.init();
        	
        	InitItemTools.init();
        	
        	ItemToolRecipes.init();
        	
        	InitBlocks.init();
        	
        	BlockRecipes.init();   	
        }
        
               
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
        }
        
              
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {

        }
}


