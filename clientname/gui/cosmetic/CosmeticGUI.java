package clientname.gui.cosmetic;

import java.awt.Color;
import java.util.ArrayList;

import clientname.cosmetics.impl.CosmeticCape;
import clientname.util.render.ColorMode;
import clientname.util.render.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class CosmeticGUI extends GuiScreen {
	
	Minecraft mc = Minecraft.getMinecraft();
	
	FontRenderer font = mc.fontRendererObj;
	ArrayList<CosmeticComponent> cosButtons = new ArrayList<CosmeticComponent>();
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		DrawUtil.drawRoundedRect(50, 50, 900, 475, 10, new Color(0, 0, 0, 150).getRGB());
		
		DrawUtil.drawRoundedRect(50, 20, 205, 50, 10, ColorMode.getBgColor()); // 0, 0, 0, 150
		DrawUtil.setColor(-1);
		font.drawString("Cosmetics", 60, 25, new Color(255, 255, 255, 255).getRGB());
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.8, 0.8, 0.8);
		font.drawString("Capes, hats, and other cosmetics.", 75, 45, -1);
		GlStateManager.popMatrix();
		Gui.drawRect(60, 35, 130, 34, -1);
		
		if(CosmeticCape.ownsCosmetic()) {cosButtons.add(new CosmeticComponent(mouseX, mouseY, 575, 75, new CosmeticCape(null), 1));}
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		for(CosmeticComponent cos : cosButtons) {
			cos.onClick(mouseX, mouseY);
		}
	}
}
