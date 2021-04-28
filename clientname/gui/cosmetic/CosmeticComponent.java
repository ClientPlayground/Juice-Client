package clientname.gui.cosmetic;

import java.awt.Color;

import clientname.cosmetics.CosmeticBase;
import clientname.util.render.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class CosmeticComponent {
	
	Minecraft mc = Minecraft.getMinecraft();
	FontRenderer font = mc.fontRendererObj;
	CosmeticBase cos;
	
	int x, y;
	public static int buttonX;
	public static int buttonY;
	public static int buttonW;
	public static int buttonH;
	public static int buttonId;
	
	public CosmeticComponent(int mouseX, int mouseY, int x, int y, CosmeticBase c, int buttonId) {
		this.cos = c;
		this.x = x;
		this.y = y;
		this.buttonX = x;
		this.buttonY = y + 120;
		this.buttonW = x + 110;
		this.buttonH = y + 150;
		this.buttonId = buttonId;
		DrawUtil.drawRoundedRect(x, y, x + 110, y + 150, 10, new Color(0, 0, 0, 160).getRGB());
		//DrawUtil.drawRoundedRect(x, y + 120, x + 110, y + 150, 10, c.wearing ? new Color(0, 255, 0, 255).getRGB() : new Color(255, 0, 0, 255).getRGB());
		//Gui.drawRect(x, y + 120, x + 110, y + 150, getWearingColor(c));
		Gui.drawRect(buttonX, buttonY, buttonW, buttonH, getWearingColor(c));
		font.drawString(c.name, x + 36, y + 5, -1);
		font.drawString(getWearingString(c), getWearingTextPos(c), y + 130, -1);
		/*this.buttonX = x;
		this.buttonY = y + 120;
		this.buttonW = x + 110;
		this.buttonH = y + 150;*/
	}
	
	private String getWearingString(CosmeticBase c) {
		if(c.wearing) {
			return "Wearing";
		} else {
			return "Not Wearing";
		}
	}
	
	private int getWearingColor(CosmeticBase c) {
		if(c.wearing) {
			return new Color(0, 255, 0, 255).getRGB();
		} else {
			 return new Color(255, 0, 0, 255).getRGB();
		}
	}
	
	private int getWearingTextPos(CosmeticBase c) {
		if(c.wearing) {
			return x + 36;
		} else {
			 return x + 29;
		}
	}
	

	public static void toggleWearing(CosmeticBase c) {
		c.toggleWearing();
	}
	
	public void onClick(int mouseX, int mouseY) {
		if(mouseX >= buttonX && mouseX <= buttonX + buttonW && mouseY >= buttonY && mouseY <= buttonY + buttonH) {
			toggleWearing(cos);
		}
	}

}
