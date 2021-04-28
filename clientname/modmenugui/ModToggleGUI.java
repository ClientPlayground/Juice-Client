package clientname.modmenugui;

import clientname.util.render.JColor;

public class ModToggleGUI {

    public static NumberSetting animationSpeed = new NumberSetting("Animation Speed", 200, 10, 500, 10);
    public static ColorSetting fontColor = new ColorSetting("fontColor", new JColor(255, 255, 255, 255));
    public static ColorSetting outlineColor = new ColorSetting("Outline Color", new JColor(255, 255, 255, 255));
    public static NumberSetting opacity = new NumberSetting("Opacity", 255, 0, 255, 10);
    public static ColorSetting backgroundColor = new ColorSetting("Background", new JColor(0, 0, 0, 255));
    public static ColorSetting activeColor = new ColorSetting("enabledColor", new JColor(36, 140, 237, 255));
    public static ColorSetting inactiveColor = new ColorSetting("Disabled Color", new JColor(255, 0, 0, 255));
    public static ColorSetting settingBackgroundColor = new ColorSetting("settinBgColor", new JColor(0, 0, 0, 255));

    //color model shit
    public static ModeSetting colorModel = new ModeSetting("Color Model", "RGB", "RGB", "HSB");


}
