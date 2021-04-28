package clientname.extras;


import clientname.event.EventTarget;
import clientname.event.impl.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

public class MemoryFix {

    private int messageDelay = 0;
    private IChatComponent updateMessage;

    @EventTarget
    public void onTick(ClientTickEvent e) {
        if (this.updateMessage != null && Minecraft.getMinecraft().thePlayer != null && ++this.messageDelay == 80) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(this.updateMessage);
            this.updateMessage = null;
        }

    }

}