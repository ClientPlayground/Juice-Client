package clientname.util.render;

import java.awt.Color;

public class ColorMode {
	
	public static boolean mode = false;
	
	public static boolean getMode() {
		return mode;
	}
	
	public static void setMode(boolean mode) {
		ColorMode.mode = mode;
	}
	
	public static void toggleMode() {
		setMode(!mode);
	}
	
	public static int getBgColor() {
		if(mode) {
			return new Color(90, 90, 90, 180).getRGB();
		}
		if(!mode) {
			return new Color(0, 0, 0, 150).getRGB();
		} else {
			return -1;
		}
	}
	public static int getModColor() {
		if(mode) {
			return new Color(111, 111, 111, 255).getRGB();
		}
		if(!mode) {
			return new Color(0, 0, 0, 255).getRGB();
		} else {
			return -1;
		}
	}
}
