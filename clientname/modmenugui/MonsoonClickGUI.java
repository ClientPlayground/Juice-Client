package clientname.modmenugui;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.ClickGUI;
import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.mc8forge.MinecraftGUI;
import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.EnumComponent;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;

import clientname.mods.ModDraggable;
import clientname.mods.ModInstances;
import net.minecraft.client.Minecraft;

public class MonsoonClickGUI extends MinecraftGUI {
	private Toggleable colorToggle;
	private final GUIInterface guiInterface;
	private final Theme theme;
	private final ClickGUI gui;
	public static MonsoonClickGUI instance = new MonsoonClickGUI();
	public final int width=100,HEIGHT=12,DISTANCE=10,HUD_BORDER=2;

	public MonsoonClickGUI() {
		colorToggle = new Toggleable() {
			@Override
			public boolean isOn() {
				return ModToggleGUI.colorModel.is("RGB");
			}

			@Override
			public void toggle() {
				
				ModToggleGUI.colorModel.increment();
				
			}
		};
		guiInterface=new GUIInterface(true) {
			@Override
			protected String getResourcePrefix() {
				return "monsoon:gui/";
			}
			
			@Override
			public void drawString(Point pos, String s, Color c) {
				end();
				Minecraft.getMinecraft().fontRendererObj.drawString(s,pos.x,pos.y,c.getRGB());
				begin();
			}
			
			@Override
			public int getFontWidth(String s) {
				return Minecraft.getMinecraft().fontRendererObj.getStringWidth(s);
			}
 
			@Override
			public int getFontHeight() {
				return Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
			}
		};
		theme=new MonsoonTheme(new SettingsColorScheme(ModToggleGUI.activeColor, ModToggleGUI.backgroundColor,ModToggleGUI.settingBackgroundColor,ModToggleGUI.outlineColor,ModToggleGUI.fontColor,ModToggleGUI.opacity),false, HEIGHT,2);
		gui=new ClickGUI(guiInterface,null);
		// Populate the ClickGUI with modules and settings
		for (Category category: Category.values()) {
				DraggableContainer panel=new DraggableContainer("  " + category.name,null,theme.getPanelRenderer(),new SimpleToggleable(false),new SettingsAnimation(ModToggleGUI.animationSpeed),null,new Point(category.pos,90),width); // <-- Width and default position of the panels needs to be defined
				gui.addComponent(panel);
			for (ModDraggable module: ModInstances.getModsByCategory(category)) {
				CollapsibleContainer container=new CollapsibleContainer(" " + module.name,module.description,theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation(ModToggleGUI.animationSpeed),module); // <-- It is recommended that the module-class implements Toggleable
				panel.addComponent(container);
				for (Setting property: module.settings) {
					if (property instanceof BooleanSetting) {
						container.addComponent(new BooleanComponent("  " + property.name,null,theme.getComponentRenderer(),(BooleanSetting)property));
					} else if (property instanceof NumberSetting) {
						container.addComponent(new NumberComponent("  " + property.name,null,theme.getComponentRenderer(),(NumberSetting)property,((NumberSetting)property).getMinimum(),((NumberSetting)property).getMaximum()));
					}  else if (property instanceof ModeSetting) {
						container.addComponent(new EnumComponent("  " + property.name,null,theme.getComponentRenderer(),(ModeSetting)property));
					} else if (property instanceof ColorSetting) {
						container.addComponent(new SyncableColorComponent(theme,(ColorSetting)property,colorToggle,new SettingsAnimation(ModToggleGUI.animationSpeed)));
					}
				}
			}
		}
		
	}

	@Override
	protected ClickGUI getGUI() {
		return gui;
	}

	@Override
	protected GUIInterface getInterface() {
		return guiInterface;
	}

	@Override
	protected int getScrollSpeed() {
		return 10;
	}
}