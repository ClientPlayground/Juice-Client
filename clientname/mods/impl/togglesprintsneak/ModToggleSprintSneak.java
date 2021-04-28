package clientname.mods.impl.togglesprintsneak;

import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.Category;
import clientname.mods.ModDraggable;
import net.minecraft.client.Minecraft;

public class ModToggleSprintSneak extends ModDraggable {
	
	public ModToggleSprintSneak() {
		super("ToggleSprint/Sneak/Flyboost", "Toggles if you're sprinting/sneaking/flyboosting (x2) or not.", Category.PLAYER, 3);
	}

	public static boolean toggleSprint = true;

    public static String current = "starting";
    
    private String textToRender = "";
    public boolean flyBoost = true;
	public float flyBoostFactor = 2;
	public int keyHoldTicks = 7;

    @Override
    public int getWidth() {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth("[Sprinting (Toggled)]");
    }

    @Override
    public int getHeight() {
        return Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
    textToRender = mc.thePlayer.movementInput.getDisplayText();
    font.drawString(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
    
    @Override 
    public void renderDummy(ScreenPosition pos) {
        Minecraft.getMinecraft().fontRendererObj.drawString("[Sprinting (Toggled)]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
    }
}