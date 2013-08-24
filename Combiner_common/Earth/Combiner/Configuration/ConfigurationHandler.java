package Earth.Combiner.Configuration;

import java.io.File;
import java.util.logging.Level;
import Earth.Combiner.lib.Ints;
import Earth.Combiner.lib.References;
import Earth.Combiner.lib.Strings;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {
	
	public static Configuration config;
	
	public static void init(File file){
		
		config = new Configuration(file);

		try{
			config.load();

			//Ints.IDS = config.get(config.CATEGORY_BLOCK, Strings.NAMES, Ints.DEFAULT_IDS).getInt(Ints.DEFAULT_IDS);
			//Ints.IDS = config.get(config.CATEGORY_ITEM, Strings.NAMES, Ints.DEFAULT_IDS).getInt(Ints.DEFAULT_IDS);
			
			//Blocks
			Ints.COMBMACHINEOFF_ID = config.get(config.CATEGORY_BLOCK, Strings.COMB_MACHINEOFF_NAME, Ints.COMBMACHINEOFF_ID_DEFAULT).getInt(Ints.COMBMACHINEOFF_ID_DEFAULT);
			Ints.COMBMACHINEON_ID = config.get(config.CATEGORY_BLOCK, Strings.COMB_MACHINEON_NAME, Ints.COMBMACHINEON_ID_DEFAULT).getInt(Ints.COMBMACHINEON_ID_DEFAULT);
			Ints.COALHEATEROFF_ID = config.get(config.CATEGORY_BLOCK, Strings.COAL_HEATEROFF_NAME, Ints.COALHEATEROFF_ID_DEFAULT).getInt(Ints.COALHEATEROFF_ID_DEFAULT);
			Ints.COALHEATERON_ID = config.get(config.CATEGORY_BLOCK, Strings.COAL_HEATERON_NAME, Ints.COALHEATERON_ID_DEFAULT).getInt(Ints.COALHEATERON_ID_DEFAULT);
			Ints.STONEGLASS_ID = config.get(config.CATEGORY_BLOCK, Strings.STONEGLASS_NAME, Ints.STONEGLASS_ID_DEFAULT).getInt(Ints.STONEGLASS_ID_DEFAULT);
			Ints.STONEPLANK_ID = config.get(config.CATEGORY_BLOCK, Strings.STONEPLANK_NAME, Ints.STONEPLANK_ID_DEFAULT).getInt(Ints.STONEPLANK_ID_DEFAULT);
			
			// Items
			Ints.ROUNDSTONE_ID = config.get(config.CATEGORY_ITEM, Strings.ROUNDSTONE_NAME, Ints.ROUNDSTONE_ID_DEFAULT).getInt(Ints.ROUNDSTONE_ID_DEFAULT);
			Ints.COMBSTONE_ID = config.get(config.CATEGORY_ITEM, Strings.COMBSTONE_NAME, Ints.COMBSTONE_ID_DEFAULT).getInt(Ints.COMBSTONE_ID_DEFAULT);
			Ints.COMBIRON_ID = config.get(config.CATEGORY_ITEM, Strings.COMBIRON_NAME, Ints.COMBIRON_ID_DEFAULT).getInt(Ints.COMBIRON_ID_DEFAULT);
			Ints.COMBGOLD_ID = config.get(config.CATEGORY_ITEM, Strings.COMBGOLD_NAME, Ints.COMBGOLD_ID_DEFAULT).getInt(Ints.COMBGOLD_ID_DEFAULT);
			Ints.COMBEMERALD_ID = config.get(config.CATEGORY_ITEM, Strings.COMBEMERALD_NAME, Ints.COMBEMERALD_ID_DEFAULT).getInt(Ints.COMBEMERALD_ID_DEFAULT);
			Ints.WOODSCRAP_ID = config.get(config.CATEGORY_ITEM, Strings.WOODSCRAP_NAME, Ints.WOODSCRAP_ID_DEFAULT).getInt(Ints.WOODSCRAP_ID_DEFAULT);
			Ints.COBBLESCRAP_ID = config.get(config.CATEGORY_ITEM, Strings.COBBLESCRAP_NAME, Ints.COBBLESCRAP_ID_DEFAULT).getInt(Ints.COBBLESCRAP_ID_DEFAULT);
			Ints.COMPWOODSCRAP_ID = config.get(config.CATEGORY_ITEM, Strings.COMPWOODSCRAP_NAME, Ints.COMPWOODSCRAP_ID_DEFAULT).getInt(Ints.COMPWOODSCRAP_ID_DEFAULT);
			Ints.COMPCOALSCRAP_ID = config.get(config.CATEGORY_ITEM, Strings.COMPCOALSCRAP_NAME, Ints.COMPCOALSCRAP_ID_DEFAULT).getInt(Ints.COMPCOALSCRAP_ID_DEFAULT);
			
			//Tools
			Ints.TOOLSTONE_ID = config.get(config.CATEGORY_ITEM, Strings.COBBLETOOL_NAME, Ints.TOOLSTONE_ID_DEFAULT).getInt(Ints.TOOLSTONE_ID_DEFAULT);
			Ints.TOOLIRON_ID = config.get(config.CATEGORY_ITEM, Strings.IRONTOOL_NAME, Ints.TOOLIRON_ID_DEFAULT).getInt(Ints.TOOLIRON_ID_DEFAULT);
			Ints.TOOLGOLD_ID = config.get(config.CATEGORY_ITEM, Strings.GOLDTOOL_NAME, Ints.TOOLGOLD_ID_DEFAULT).getInt(Ints.TOOLGOLD_ID_DEFAULT);
			Ints.TOOLEMERALD_ID = config.get(config.CATEGORY_ITEM, Strings.EMERALDTOOL_NAME, Ints.TOOLEMERALD_ID_DEFAULT).getInt(Ints.TOOLEMERALD_ID_DEFAULT);
			


		}
		catch(Exception e){
			FMLLog.log(Level.SEVERE, e, References.MODID + "Has a problem loading the config file");
		}
		finally{
			config.save();
		}

	}
}
