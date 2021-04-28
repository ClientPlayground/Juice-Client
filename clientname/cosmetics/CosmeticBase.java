package clientname.cosmetics;

import com.lukflug.panelstudio.settings.Toggleable;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public abstract class CosmeticBase implements LayerRenderer<AbstractClientPlayer>, Toggleable{


	public static RenderPlayer playerRenderer;
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static String name;
	public static boolean cape;
	public boolean wearing;
	public boolean owns;

	public CosmeticBase(RenderPlayer playerRenderer) {
		this.playerRenderer = playerRenderer;
	}
	
	public static RenderPlayer getPlayerRenderer() {
		return playerRenderer;
	}

	public static Minecraft getMc() {
		return mc;
	}

	public static String getName() {
		return name;
	}

	public static boolean isCape() {
		return cape;
	}

	public boolean isOwns() {
		return owns;
	}

	public void setWearing(boolean newWearing) {
		wearing = newWearing;
	}
	
	public boolean isWearing() {
		return wearing;
	}
	
	public void toggleWearing() {
		if(owns) {
			wearing = !wearing;
		}
	}
	
	@Override
	public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if(player.hasPlayerInfo() && !player.isInvisible()) {
			render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
		}
		
	}
	
	public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale);

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
	
	@Override
	public boolean isOn() {
		return wearing && owns;
	}
	
	@Override
	public void toggle() {
		if(owns) {
			wearing = !wearing;
		}
	}

}
