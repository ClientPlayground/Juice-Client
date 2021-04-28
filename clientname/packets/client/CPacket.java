package clientname.packets.client;

import java.io.IOException;

import clientname.packets.ECPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public abstract class CPacket extends ECPacket{

	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		// sending from client
		
	}
	
	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		// sending from client
		
	}
	
}
