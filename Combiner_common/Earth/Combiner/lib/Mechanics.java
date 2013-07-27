package Earth.Combiner.lib;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import Earth.Combiner.CombinerCore;
import Earth.Combiner.MachineUtilety.GuiHandler;
import Earth.Combiner.MachineUtilety.TileEntityCombMachine;

public class Mechanics {
	
	private static GuiHandler guihandler = new GuiHandler();
	
	public static void init(){
		
		// Blocks mechanics register
        GameRegistry.registerTileEntity(TileEntityCombMachine.class, "tileentitycombmachine");
        NetworkRegistry.instance().registerGuiHandler(CombinerCore.instance, guihandler);
		
	}
}
