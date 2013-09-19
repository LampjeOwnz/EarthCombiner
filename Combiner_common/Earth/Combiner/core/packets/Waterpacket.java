package Earth.Combiner.core.packets;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class Waterpacket extends Earthpackets {


    private int randomInteger;
    private int randomInt;
    
    public Waterpacket(int randomInteger) {
            this.randomInteger = randomInteger;
    }

    public Waterpacket() { } // Be sure to always have the default constructor in your class, or the reflection code will fail!

    @Override
	public void write(ByteArrayDataOutput out) {
            out.writeInt(randomInt);
    }

    @Override
    public void read(ByteArrayDataInput in) throws ProtocolException {
            randomInt = in.readInt();
    }

    @Override
    public void execute(EntityPlayer player, Side side) throws ProtocolException {
            if (side.isClient()) {
                    // Crap... Umm, aside from that, I can't remember what I am doing. You basically have to make it so that the GUI gets the integer... I really can't remember... I know I have done it before, but I would have to double check code. I believe that I remember thinking it redundant.... So don't think it's redundant. I will be back with some working stuff XD
            } else {
                    throw new ProtocolException("Cannot send this packet to the server!");
            }
    }
}
