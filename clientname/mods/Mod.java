package clientname.mods;

import com.lukflug.panelstudio.settings.Toggleable;

import clientname.Client;
import clientname.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod implements Toggleable {

	private boolean isEnabled = true;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Client client;
	
	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = this.mc.fontRendererObj;
		this.client = Client.getInstance();
		
		setEnabled(isEnabled);
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		
		if(isEnabled) {
			EventManager.register(this);
		}
		else {
			EventManager.unregister(this);
		}
	}
	public void toggle() {
		setEnabled(!isEnabled);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public boolean isOn() {
		return isEnabled;
	}

}
