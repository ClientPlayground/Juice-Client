package clientname.modmenugui;


public class BooleanSetting extends Setting implements com.lukflug.panelstudio.settings.Toggleable {

	public static boolean enabled;

	public BooleanSetting(String name, boolean enabled) {
		this.name = name;
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void toggle() {
		enabled = !enabled;
		
	}

	@Override
	public boolean isOn() {
		return enabled;
	}
}
