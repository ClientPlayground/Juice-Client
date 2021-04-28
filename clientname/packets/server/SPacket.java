package clientname.packets.server;

import java.io.IOException;

import clientname.packets.ECPacket;
import net.minecraft.network.PacketBuffer;

public abstract class SPacket extends ECPacket{

	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
		// Sent from the server
	}
	
}
