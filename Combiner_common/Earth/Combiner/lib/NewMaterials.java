package Earth.Combiner.lib;

import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class NewMaterials {

	// The new tool materials
	public static EnumToolMaterial NewSTONE;//= EnumHelper.addToolMaterial("NewSTONE", 1, 262, 6F, 1, 10);
	public static EnumToolMaterial NewIRON;//= EnumHelper.addToolMaterial("NewIRON", 2, 500, 8F, 2, 18);
	public static EnumToolMaterial NewGOLD;//= EnumHelper.addToolMaterial("NewGOLD", 0, 64, 14F, 0, 24);
	public static EnumToolMaterial NewEMERALD;//= EnumHelper.addToolMaterial("NewEMERALD", 3, 3122, 11F, 3, 20);
	
	public static void init(){
		
		// Tool material + strength
		NewSTONE= EnumHelper.addToolMaterial("NewSTONE", 1, 262, 6F, 1, 10);
		NewIRON= EnumHelper.addToolMaterial("NewIRON", 2, 500, 8F, 2, 18);
		NewGOLD= EnumHelper.addToolMaterial("NewGOLD", 0, 64, 14F, 0, 24);
		NewEMERALD= EnumHelper.addToolMaterial("NewEMERALD", 3, 3122, 11F, 3, 20);
	}
}
