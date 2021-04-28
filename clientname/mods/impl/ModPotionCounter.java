package clientname.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.Category;
import clientname.mods.InventoryUtils;
import clientname.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModPotionCounter extends ModDraggable{
	
	public ModPotionCounter() {
		super("Potion Counter", "Displays how many pots you have left", Category.HUD, 2);
	}

	@Override
	public int getWidth() {
		return font.getStringWidth(InventoryUtils.getPotionsFromInventory() + "Pots");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if(InventoryUtils.getPotionsFromInventory() > 1) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + Minecraft.getMinecraft().fontRendererObj.getStringWidth(InventoryUtils.getPotionsFromInventory() + "Pots") + 4, pos.getAbsoluteY() + 12, 1140850688);
	        Minecraft.getMinecraft().fontRendererObj.drawString(InventoryUtils.getPotionsFromInventory() + " Pots", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float) (System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F));
		}
		else if(InventoryUtils.getPotionsFromInventory() == 1) {
        	Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + Minecraft.getMinecraft().fontRendererObj.getStringWidth(InventoryUtils.getPotionsFromInventory() + "Pot") + 4, pos.getAbsoluteY() + 12, 1140850688);
        	Minecraft.getMinecraft().fontRendererObj.drawString(InventoryUtils.getPotionsFromInventory() + " Pot", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float) (System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F));
        }
		else {
        	Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + Minecraft.getMinecraft().fontRendererObj.getStringWidth("NO POTS LEFT") + 4, pos.getAbsoluteY() + 12, 1140850688);
        	Minecraft.getMinecraft().fontRendererObj.drawString("NO POTS LEFT", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, Color.HSBtoRGB((float) (System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F));
        }
        if (GL11.glIsEnabled(3042)) {
            GL11.glEnable(3042);
        }
	}
	
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
		font.drawString(InventoryUtils.getPotionsFromInventory() + "Pots", pos.getAbsoluteX(), pos.getAbsoluteY(), 0x0000FFFF);
	}

}
