package clientname.modmenugui;

import com.lukflug.panelstudio.settings.Toggleable;


public class ColorToggleUtil implements Toggleable {
	
	public static Toggleable colorModel;

	@Override
	public boolean isOn() {
		return ModToggleGUI.colorModel.is("RGB");
	}

	@Override
	public void toggle() {
		
		ModToggleGUI.colorModel.increment();
		
	}

}
