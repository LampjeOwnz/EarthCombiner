package Earth.Combiner.core.handlers;

import Earth.Combiner.Utilety.CoalHeaterUtilety.ContainerCoalHeater;
import Earth.Combiner.Utilety.CoalHeaterUtilety.TileEntityCoalHeater;
import Earth.Combiner.Utilety.CoalHeaterUtilety.GuiCoalHeater;
import Earth.Combiner.Utilety.MachineUtilety.ContainerCombMachine;
import Earth.Combiner.Utilety.MachineUtilety.GuiCombMachine;
import Earth.Combiner.Utilety.MachineUtilety.TileEntityCombMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if(tile_entity instanceof TileEntityCombMachine) {
			return new ContainerCombMachine(player.inventory, (TileEntityCombMachine) tile_entity);
		}
		
		if(tile_entity instanceof TileEntityCoalHeater) {
			return new ContainerCoalHeater(player.inventory, (TileEntityCoalHeater) tile_entity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if(tile_entity instanceof TileEntityCombMachine) {
			return new GuiCombMachine(player.inventory, (TileEntityCombMachine) tile_entity);
		}
		
		if(tile_entity instanceof TileEntityCoalHeater) {
			return new GuiCoalHeater(player.inventory, (TileEntityCoalHeater) tile_entity);
		}
		
		return null;
	}

}


