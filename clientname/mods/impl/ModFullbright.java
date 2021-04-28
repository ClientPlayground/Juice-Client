package clientname.mods.impl;

import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.Category;
import clientname.mods.ModDraggable;

public class ModFullbright extends ModDraggable{

	public ModFullbright() {
		super("Fullbright", "Makes the world Brighter. Not allowed on some servers.", Category.PLAYER, 2);
	}
	
	private ScreenPosition pos;

	@Override
	public int getWidth() {
		return font.getStringWidth("[Fullbright On]");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("[Fullbright On]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("[Fullbright On]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;;
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}

}
