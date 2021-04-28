package clientname.packets.server;

import java.io.IOException;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SpacketHelloWorld extends SPacket{

	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		System.err.println("The server says HELLO!");
	}
	
}
