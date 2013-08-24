package Earth.Combiner.core.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import Earth.Combiner.lib.References;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public abstract class Earthpackets {

        private static final BiMap<Integer, Class<? extends Earthpackets>> idMap;
                
        static {
                ImmutableBiMap.Builder<Integer, Class<? extends Earthpackets>> builder = ImmutableBiMap.builder();
                
                builder.put(Integer.valueOf(0), Waterpacket.class);
                // more packets like this:
                // builder.put(Integer.valueOf(1), AnotherPacket.class);
                // builder.put(Integer.valueOf(2), YetAnotherPacket.class);
                
                idMap = builder.build();
        }

        public static Earthpackets constructPacket(int packetId) throws ProtocolException, ReflectiveOperationException {
                Class<? extends Earthpackets> clazz = idMap.get(Integer.valueOf(packetId));
                if (clazz == null) {
                        throw new ProtocolException("Unknown Packet Id!");
                } else {
                        return clazz.newInstance();
                }
        }

        public static class ProtocolException extends Exception {

                public ProtocolException() {
                }

                public ProtocolException(String message, Throwable cause) {
                        super(message, cause);
                }

                public ProtocolException(String message) {
                        super(message);
                }

                public ProtocolException(Throwable cause) {
                        super(cause);
                }
        }

        public final int getPacketId() {
                if (idMap.inverse().containsKey(getClass())) {
                        return idMap.inverse().get(getClass()).intValue();
                } else {
                        throw new RuntimeException("Packet " + getClass().getSimpleName() + " is missing a mapping!");
                }
        }
        
        public final Packet makePacket() {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeByte(getPacketId());
                write(out);
                return PacketDispatcher.getPacket(References.CHANNEL, out.toByteArray());
        }

        public abstract void write(ByteArrayDataOutput out);
        
        public abstract void read(ByteArrayDataInput in) throws ProtocolException;
        
        public abstract void execute(EntityPlayer player, Side side) throws ProtocolException;
}
