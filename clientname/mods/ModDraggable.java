package clientname.mods;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lukflug.panelstudio.settings.Toggleable;

import clientname.FileManager;
import clientname.event.EventManager;
import clientname.gui.hud.IRenderer;
import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.Category;
import clientname.modmenugui.Setting;


public abstract class ModDraggable extends Mod implements IRenderer, Toggleable {
	
	protected ScreenPosition pos;
	public boolean isEnabled = false;
	public final String name;
	public String displayname;
	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public final String description;
	protected final Category category;
	protected final int side;
	public static boolean drawn;
	public List<Setting> settings = new ArrayList<Setting>();
	
	public ModDraggable(String name, String description, Category category, int side) {
		pos = loadPositionFromFile();
		this.name = name;
		this.category = category;
		this.side = side;
		this.description = description;
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
	}
	

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		saveInfoToFile();
	}
	
	private File getFolder() {
		File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
		folder.mkdirs();
		return folder; 
	}
	
	private void saveInfoToFile() {
		FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
		//FileManager.writeJsonToFile(new File(getFolder(), "info.json"), isEnabled);
	}


	private ScreenPosition loadPositionFromFile() {
		
		ScreenPosition loaded = FileManager.readFromJson(new File(getFolder(), "pos.json"), ScreenPosition.class);
		
		if(loaded == null) {
			loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
			this.pos = loaded;
			saveInfoToFile();
		}
		return loaded;
	}
	
	@SuppressWarnings("null")
	private boolean loadEnabledFromFile() {

		boolean loaded = FileManager.readFromJson(new File(getFolder(), "info.json"), Boolean.class);
		
		if(loaded = (Boolean) null) {
			loaded = false;
			this.isEnabled = loaded;
			saveInfoToFile();
		}
		return loaded;
	}

	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(pos, lineNum);
	}
	
	public String getName() {
		return name;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		if(isEnabled) {
			onEnable();
		}
		else {
			onDisable();
		}
		
	}
	
	public void onEnable() {EventManager.register(this);}
	public void onDisable() {EventManager.unregister(this);}
	public void onUpdate() {}
	
	@Override
	public void toggle() {
		setEnabled(!isEnabled);
	}
	
	public void toggleSpecfific(ModDraggable mod) {
		mod.setEnabled(!isEnabled);
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}


	public Category getCategory() {
		return category;
	}
	
	public int getSide() {
		return side;
	}
	
	@Override
	public boolean isOn() {
		return isEnabled;
	}
	
	public boolean isToggled() {
		return isEnabled;
	}
	
	public void setToggled(boolean toggled) {
		setEnabled(toggled);
	}
	
	
}