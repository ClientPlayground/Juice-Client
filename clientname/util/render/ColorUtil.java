package clientname.util.render;

import java.awt.Color;

public class ColorUtil {

	public static int getRainbow(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int)(seconds * 1000)) / (float)(seconds * 4000f);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
	
	public static int getRGB(float seconds, float saturation, float brightness, long index) {
		float hue = ((System.currentTimeMillis() + index) % (int)(seconds * 1000)) / (float)(seconds * 1000);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}

	 public static Color astolfo(int index, int speed, float saturation, float brightness, float opacity) {
        int angle = (int) ((System.currentTimeMillis() / speed + index) % 360);
        angle = (angle > 180 ? 360 - angle : angle) + 180;
        float hue = angle / 360f;

        int color = Color.HSBtoRGB(brightness, saturation, hue);
        Color obj = new Color(color);
        return new Color(obj.getRed(), obj.getGreen(), obj.getBlue(), Math.max(0, Math.min(255, (int) (opacity * 255))));
    }

}
