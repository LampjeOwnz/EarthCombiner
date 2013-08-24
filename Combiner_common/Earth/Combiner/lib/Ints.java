package Earth.Combiner.lib;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import Earth.Combiner.CombinerCore;
import Earth.Combiner.Utilety.MachineUtilety.TileEntityCombMachine;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Ints {

	public static Configuration config;
	
	// Item ID's
	public static final int ROUNDSTONE_ID_DEFAULT = 17070;
	public static final int COMBSTONE_ID_DEFAULT = 17071;
	public static final int COMBIRON_ID_DEFAULT = 17072;
	public static final int COMBGOLD_ID_DEFAULT = 17073;
	public static final int COMBEMERALD_ID_DEFAULT = 17074;
	public static final int WOODSCRAP_ID_DEFAULT = 17075;
	public static final int COBBLESCRAP_ID_DEFAULT = 17076;
	public static final int COMPWOODSCRAP_ID_DEFAULT = 17077;
	public static final int COMPCOALSCRAP_ID_DEFAULT = 17078;
	public static int ROUNDSTONE_ID;
	public static int COMBSTONE_ID;
	public static int COMBIRON_ID;
	public static int COMBGOLD_ID;
	public static int COMBEMERALD_ID;
	public static int WOODSCRAP_ID;
	public static int COBBLESCRAP_ID;
	public static int COMPWOODSCRAP_ID;
	public static int COMPCOALSCRAP_ID;
	
	// Tool ID's
	public static final int TOOLSTONE_ID_DEFAULT = 17180;
	public static final int TOOLIRON_ID_DEFAULT = 17181;
	public static final int TOOLGOLD_ID_DEFAULT = 17182;
	public static final int TOOLEMERALD_ID_DEFAULT = 17183;
	public static int TOOLSTONE_ID;
	public static int TOOLIRON_ID;
	public static int TOOLGOLD_ID;
	public static int TOOLEMERALD_ID;
		
	// Block ID's
	public static final int COMBMACHINEOFF_ID_DEFAULT = 2356;
	public static final int COMBMACHINEON_ID_DEFAULT = 2357;
	public static final int COALHEATEROFF_ID_DEFAULT = 2358;
	public static final int COALHEATERON_ID_DEFAULT = 2359;
	public static final int STONEGLASS_ID_DEFAULT = 2360;
	public static final int STONEPLANK_ID_DEFAULT = 2361;
	public static int COMBMACHINEOFF_ID;
	public static int COMBMACHINEON_ID;
	public static int COALHEATEROFF_ID;
	public static int COALHEATERON_ID;
	public static int STONEGLASS_ID;
	public static int STONEPLANK_ID;
	
	
	// TileEntityCombMachine ints
	public static int INPUT1 = 0;
	public static int INPUT2 = 2;
	public static int FUELSLOT = 1;
	public static int OUTPUT = 3;
	public static int UPGRADESLOT = 4;
	
	// TileEntityCoalHeater ints
	public static int WATERSLOT = 0;
	public static int BURNSLOT = 1;
	public static int BUCKETOUT = 2;
	
}

