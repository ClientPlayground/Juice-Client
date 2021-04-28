package clientname.mods;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import clientname.gui.hud.HUDManager;
import clientname.modmenugui.Category;
import clientname.mods.impl.*;
import clientname.mods.impl.togglesprintsneak.ModToggleSprintSneak;

public class ModInstances {
	
	public static ArrayList<ModDraggable> modules;

	private static ModArmorStatus modArmorStatus;
	private static ModFPS modFPS;
	private static ModCoords modCoords;
	private static ModPotionStatus modPotionStatus;
	private static ModPing modPing;
	private static ModKeystrokes modKeystrokes;
	private static ModFullbright modFullbright;
	private static Mod mod;
	private static ModPerspective modPerspective;
	private static ModPotionCounter modPotionCounter;
	private static ModToggleSprintSneak modToggleSprintSneak;
	
	public static void register(HUDManager api) {
		
		modules = new ArrayList<>();

		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);
		modules.add(modArmorStatus);
		
		modCoords = new ModCoords();
		api.register(modCoords);
		modules.add(modCoords);

		modFPS = new ModFPS();
		api.register(modFPS);
		modules.add(modFPS);

		modKeystrokes = new ModKeystrokes();
		api.register(modKeystrokes);
		modules.add(modKeystrokes);

		modPing = new ModPing();
		api.register(modPing);
		modules.add(modPing);

		modPotionStatus = new ModPotionStatus();
		api.register(modPotionStatus);
		modules.add(modPotionStatus);

		modPerspective = new ModPerspective();
		api.register(modPerspective);
		modules.add(modPerspective);

		modFullbright = new ModFullbright();
		api.register(modFullbright);
		modules.add(modFullbright);

		modPotionCounter = new ModPotionCounter();
		api.register(modPotionCounter);
		modules.add(modPotionCounter);

		modToggleSprintSneak = new ModToggleSprintSneak();
		api.register(modToggleSprintSneak);
		modules.add(modToggleSprintSneak);
			
	}

	public static ModPerspective getModPerspective() {
		return modPerspective;
	}

	public static ModToggleSprintSneak getModToggleSprintSneak() {
		return modToggleSprintSneak;
	}
	

	public static ArrayList<ModDraggable> getModules() {
		return modules;
	}
	
	public static ArrayList<ModDraggable> getModsByCategory(Category c){
		ArrayList<ModDraggable> list = (ArrayList<ModDraggable>) getModules().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
		return list;
	}
	
	
	
}