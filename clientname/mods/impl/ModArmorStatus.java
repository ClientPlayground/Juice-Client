package clientname.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.BooleanSetting;
import clientname.modmenugui.Category;
import clientname.modmenugui.ModeSetting;
import clientname.modmenugui.NumberSetting;
import clientname.mods.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModArmorStatus extends ModDraggable {

	BooleanSetting hor = new BooleanSetting("Horizontal", true);
	ModeSetting mode = new ModeSetting("Test", "Test1", "Test1", "Test2", "Test3");
	NumberSetting test = new NumberSetting("Test fucking two", 100, 10, 500, 10);
	
	public ModArmorStatus() {
		super("Armor Status", "Displays your Armor", Category.HUD, 1);
		super.addSettings(hor, mode, test);
	}
	
	@Override
	public int getWidth() {
		if(hor.isEnabled()) {
		    return 63;
		} else {
			return 15;
		}
	}

	@Override
	public int getHeight() {
		if(hor.isEnabled()) {
		    return 15;
		} else {
			return 63;
		}
	}


	@Override
	public void render(ScreenPosition pos) {
		if(hor.isEnabled()) {
		     mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(3), pos.getAbsoluteX(), pos.getAbsoluteY() + 2);
		     mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(2), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + 2);
		     mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(1), pos.getAbsoluteX() + 40, pos.getAbsoluteY() + 2);
		     mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(0), pos.getAbsoluteX() + 60, pos.getAbsoluteY() + 2);
		} else {
			for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
				renderItemStack(pos, i, itemStack);
			}
		}
		
	}
	
	public void renderDummy(ScreenPosition pos) {
		if(hor.isEnabled()) {
			font.drawString("IOU armor", pos.getAbsoluteX(), pos.getAbsoluteY(), -2);
		} else {
			for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
				renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
				renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
				renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
				renderItemStack(pos, 0, new ItemStack(Items.diamond_boots)); 
			}
		}
		
	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {
		
		if(is == null) {
			return;
		}
		
		GL11.glPushMatrix();
		int yAdd = (-16 * i) + 48;
		
		/*if(is.getItem().isDamageable()) {
			double damage = ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage() * 100);
			font.drawString(String.format("%.0f", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, 0x00ff00);
		}*/
		
		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
		GL11.glPopMatrix();
		
	}
}

