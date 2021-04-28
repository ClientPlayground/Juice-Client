package clientname.mods.impl;

import java.awt.Color;

public class RainbowColor {

	public static int rainbowEffect() {
    	return Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F);
    }
}