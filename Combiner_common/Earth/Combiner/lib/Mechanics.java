package Earth.Combiner.lib;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import Earth.Combiner.CombinerCore;
import Earth.Combiner.core.handlers.GuiHandler;
import Earth.Combiner.Utilety.CoalHeaterUtilety.TileEntityCoalHeater;
import Earth.Combiner.Utilety.MachineUtilety.TileEntityCombMachine;

public class Mechanics {
	
	private static GuiHandler guihandler = new GuiHandler();
	
	public static void init(){
		
		// Blocks mechanics register
        GameRegistry.registerTileEntity(TileEntityCombMachine.class, "tileentitycombmachine");
        GameRegistry.registerTileEntity(TileEntityCoalHeater.class, "tileentitycoalheater");
        
        // Gui handler register
        NetworkRegistry.instance().registerGuiHandler(CombinerCore.instance, guihandler);;
		
	}
}
