package clientname;

import org.lwjgl.opengl.Display;

import clientname.cosmetics.CosmeticController;
import clientname.event.EventManager;
import clientname.event.EventTarget;
import clientname.event.impl.ClientTickEvent;
import clientname.extras.WebhookSender;
import clientname.gui.GuiAPIDown;
import clientname.gui.GuiHWIDBanned;
import clientname.gui.GuiWhitelisted;
import clientname.gui.SplashProgress;
import clientname.gui.hud.HUDManager;
import clientname.http.HTTPFunctions;
import clientname.http.gsonobjs.ObjectGlobalSettings;
import clientname.modmenugui.MonsoonClickGUI;
import clientname.mods.ModInstances;
import clientname.util.SessionChanger;
import net.minecraft.client.Minecraft;

public class Client {
	
	public static final Client INSTANCE = new Client();
	public final static String name = "Juice Client";
	public final static String version = "0.5.1";
	
	public ModInstances modMananager;
	public String Reason;
	
	public static final Client getInstance() {
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	private HUDManager hudManager;
	
	
	
	private boolean isAPIUp = false;
	private volatile boolean isBanned = false;
	public boolean isWhitelisted = false;
	private ObjectGlobalSettings globalSettings;
	
	public void init() {
		SessionChanger.getInstance().setUser("Minecraft@gmail.com", "Password@");
		FileManager.init();
		SplashProgress.setProgress(1, "Client - Initalising Discord!");
		discordRP.start();
		EventManager.register(this);
		
		
		isAPIUp = HTTPFunctions.isAPIUp();
		isBanned = HTTPFunctions.isBanned();
		isWhitelisted = HTTPFunctions.isWhitelisted();
		globalSettings = HTTPFunctions.downloadGlobalSettings();
		CosmeticController.downloadUserCosmetics();
		
		WebhookSender.sendMessage(":exclamation:  ALERT! :exclamation: **" + 
				Minecraft.getMinecraft().getSession().getUsername() + 
		        "** just logged into the client");
	}
	
	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
		
		HTTPFunctions.sendHWIDmap();
	}
	
	public void startup() {
		System.out.println(String.valueOf(new StringBuilder("Starting ").append(name).append(" ").append(version)));
        Display.setTitle((String)String.valueOf(new StringBuilder(String.valueOf(name)).append(" v").append(version).append(" (Beta)")));
	}
	
	public void shutdown() {
		Display.setTitle("Shutting down" + name + " " + version);
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	public void cleanGL() {}
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}
		
		if(Minecraft.getMinecraft().gameSettings.GUIModMenu.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new MonsoonClickGUI());
		}

		if(isBanned && isAPIUp && !(Minecraft.getMinecraft().currentScreen instanceof GuiHWIDBanned)) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiHWIDBanned(Reason));
		}
		
		if(globalSettings.isWhitelisted() && isAPIUp && !isWhitelisted && !(Minecraft.getMinecraft().currentScreen instanceof GuiWhitelisted)) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiWhitelisted());
		}
		
		if(isAPIUp == false) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiAPIDown());
		}
		
	}

	public int getColour() {
		return 0x00FF0000;
	}

}
