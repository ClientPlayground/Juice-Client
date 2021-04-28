package clientname.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import clientname.gui.hud.ScreenPosition;
import clientname.modmenugui.Category;
import clientname.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;

public class ModKeystrokes extends ModDraggable {
	
	public ModKeystrokes() {
		super("Keystrokes", "Displays what keys you press.", Category.HUD, 2);
	}
	
	int colour;

	public static enum KeystrokesMode {
		
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.A, Key.LMB, Key.RMB),
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key("§m-----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18, false)),
		WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.LMB, Key.RMB, Key.D, new Key("§m-----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18, false));
		
		private final Key[] keys;
		private int width = 0;
		private int height  = 0;
		
		private KeystrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
				
			}
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public Key[] getKeys() {
			return keys;
		}
		
	}
	
	private static class Key {
		
		private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18, false);
		private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18, false);
		private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18, false);
		private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18, false);
		
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18, true);
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18, true);
		
		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		private final boolean cps;
		private String Cps = "CPS";
		
		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height, boolean cps) {

			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.cps = cps;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public int getHeight() {
			return height;
		}
		
		public String getName() {
			return name;
		}
		public String getCps() {
			return Cps;
		}
		public int getWidth() {
			return width;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
	}
	
	private KeystrokesMode mode = KeystrokesMode.WASD_JUMP_MOUSE;
	
	public void setMode(KeystrokesMode mode) {
		this.mode = mode;
	}
	
	@Override
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}
	
	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;
	
	private List<Long> clicks2 = new ArrayList<Long>();
	private boolean wasPressed2;
	private long lastPressed2;
	public boolean rainbowmode = false;
	@Override
	public void render(ScreenPosition pos) {
			
			final boolean lpressed = Mouse.isButtonDown(0);
			final boolean rpressed = Mouse.isButtonDown(1);
			
			if(lpressed != this.wasPressed) {
				this.lastPressed = System.currentTimeMillis() + 10;
				this.wasPressed = lpressed;
				if(lpressed) {
					this.clicks.add(this.lastPressed);
				}
			}
			
			if(rpressed != this.wasPressed2) {
				this.lastPressed2 = System.currentTimeMillis() + 10;
				this.wasPressed2 = rpressed;
				if(rpressed) {
					this.clicks2.add(this.lastPressed2);
				}
			}
		
			GL11.glPushMatrix();
			
			for(Key key : mode.getKeys()) {
				
				int textWidth = font.getStringWidth(key.getName());
				
				
				
				if(rainbowmode == true) {
				Gui.drawRect(
						pos.getAbsoluteX() + key.getX(), 
						pos.getAbsoluteY() + key.getY(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth(), 
						pos.getAbsoluteY() + key.getY() + key.getHeight(), 
						key.isDown() ? RainbowColor.rainbowEffect() : new Color(0, 0, 0, 150).getRGB()
						);
				}else {
				Gui.drawRect(
						pos.getAbsoluteX() + key.getX(), 
						pos.getAbsoluteY() + key.getY(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth(), 
						pos.getAbsoluteY() + key.getY() + key.getHeight(), 
						key.isDown() ? Color.WHITE.getRGB() : new Color(0, 0, 0, 150).getRGB()
						);
				
				}
				font.drawString(
						key.getName(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, 
						pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, 
						key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
						);
				if(key.cps) {
					GlStateManager.pushMatrix();
					GlStateManager.scale(0.5F, 0.5F, 0.5F);
					GlStateManager.translate(pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2F , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4F, 1F);
					if(key.getName().matches(key.LMB.getName())) {
						font.drawString("CPS " + getCPS(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, -1);
					}
					
					if(key.getName().matches(key.RMB.getName())) {
						font.drawString("CPS " + getCPS2(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, -1);
					}
					GlStateManager.popMatrix();
				}
				
			}
	
			GL11.glPopMatrix();
		}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		
			GL11.glPushMatrix();
			
			for(Key key : mode.getKeys()) {
				
				int textWidth = font.getStringWidth(key.getName());
				
				Gui.drawRect(
						pos.getAbsoluteX() + key.getX(), 
						pos.getAbsoluteY() + key.getY(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth(), 
						pos.getAbsoluteY() + key.getY() + key.getHeight(), 
						key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 150).getRGB()
						);
				
				font.drawString(
						key.getName(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, 
						pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, 
						key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
						);
				
			}
	
			GL11.glPopMatrix();
		
	}
	
	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
	}
	
	private int getCPS2() {
		final long time2 = System.currentTimeMillis();
		this.clicks2.removeIf(aLong2 -> aLong2 + 1000 < time2);
		return this.clicks2.size();
	}
}