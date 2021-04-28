package clientname.mods.impl;

import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.Category;
import clientname.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModPing extends ModDraggable {
	
	public ModPing() {
		super("Ping Display", "Displays your ping.", Category.HUD, 2);
	}
	
	@Override
    public int getWidth()
    {
        return font.getStringWidth("Ping: " +  Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime());
    }

    @Override
    public int getHeight()
    {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow(Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + (" ms"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
    }

}