package Earth.Combiner.core.packets;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import Earth.Combiner.core.packets.Earthpackets.ProtocolException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class EarthpacketsHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		//if(packet.channel.equals("CombinerCore")){
		//	handlePacket(packet);
		//}
        	
		try {
			EntityPlayer entityPlayer = (EntityPlayer)player;
			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			int packetId = in.readUnsignedByte(); // Assuming your packetId is between 0 (inclusive) and 256 (exclusive). If you need more you need to change this
			Earthpackets earthPacket = Earthpackets.constructPacket(packetId);
			earthPacket.read(in);
			earthPacket.execute(entityPlayer, entityPlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} 
		catch (ProtocolException error) {
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
				Logger.getLogger("EarthMod").warning("Player " + ((EntityPlayer)player).username + " caused a Protocol Exception!");
			}
		} 
		catch (ReflectiveOperationException e) {
			throw new RuntimeException("Unexpected Reflection exception during Packet construction!", e);
		}
	}
	/*
	public void handlePacket(Packet250CustomPayload packet){
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		int randomInt1;
		int randomInt2;
		
		try{
			randomInt1 = inputStream.readInt();
			randomInt2 = inputStream.readInt();
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
		
		System.out.println(randomInt1 + "" + randomInt2);
	}
	*/
}