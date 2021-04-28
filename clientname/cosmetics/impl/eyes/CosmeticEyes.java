package clientname.cosmetics.impl.eyes;

import java.util.Random;

import clientname.cosmetics.CosmeticBase;
import clientname.cosmetics.CosmeticController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticEyes extends CosmeticBase {

	private final ModelEyes model;
	private static final ResourceLocation TEXTURE = new ResourceLocation("clientname/eyes.png");
	
	private static final float[] headJointSneak = new float[] {0F, -1F/16F, 0F};
	private static final float[] headJoint = new float[3];
	private static final float[] eyeOffset = new float[] {0F, 4F/16F, 4F/16F};
	private static final float[] irisColor = new float[] {0.9F, 0.9F, 0.9F};
	private static final float[] pupilColor = new float[] {0.0F, 0.0F, 0.0F};
	private static final float halfInterpupillaryDistance = 2F/16F;
	private static final float eyeScale = 0.75F;
	private static final float modelScale = 0.0625F;
	
	private Random rand = new Random();
	private int[] potionTime = new int[2];
	
	public CosmeticEyes(RenderPlayer playerRenderer) {
		super(playerRenderer);
		model = new ModelEyes(playerRenderer);
	}

	@Override
	public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if(!CosmeticController.shouldRenderEyes(player)) {
			return;
		}
		
		EyePhysics physics = PhysicsManager.getInstance().getPhysics(player);
		
		physics.requireUpdate();
		
		GlStateManager.enableDepth();
		GlStateManager.depthMask(true);
		
		for(int i = 0; i < 2; i++) {
			
			GlStateManager.pushMatrix();
			
			float[] joint = getSneakOffset(player, partialTicks);
			GlStateManager.translate(-joint[0], -joint[1], -joint[2]);
			
			GlStateManager.rotate(getHeadYaw(player, partialTicks), 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(getHeadPitch(player, partialTicks), 1.0F, 0.0F, 0.0F);
			
			GlStateManager.translate(-(eyeOffset[0] + (i == 0 ? halfInterpupillaryDistance : -halfInterpupillaryDistance)), -eyeOffset[1], -eyeOffset[2]);
			
			GlStateManager.scale(eyeScale, eyeScale, eyeScale * 0.5F);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			
			GlStateManager.color(irisColor[0], irisColor[1], irisColor[2]);
			model.renderIris(modelScale);
			
			GlStateManager.color(pupilColor[0], pupilColor[1], pupilColor[2]);
			float pupilScale = getPupilScale(player, partialTicks, i);
			
			GlStateManager.pushMatrix();
			GlStateManager.scale(pupilScale, pupilScale, 1F);
			
			model.movePupil(
					physics.eyes[i].prevDeltaX + (physics.eyes[i].deltaX - physics.eyes[i].prevDeltaX) * partialTicks, 
					physics.eyes[i].prevDeltaY + (physics.eyes[i].deltaY - physics.eyes[i].prevDeltaY) * partialTicks, 
					pupilScale
					);
			
			model.renderPupil(modelScale);
			
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
			
		}
		
		GlStateManager.color(1F, 1F, 1F, 1F);
		
	}
	
	//TODO: 
	private float getPupilScale(AbstractClientPlayer player, float partialTicks, int eye) {
		
		if(!player.getActivePotionEffects().isEmpty()) {
			
			rand.setSeed(Math.abs(player.hashCode()) * 1000);
			if(potionTime == null || potionTime.length < 2) {
				potionTime = new int[2];
			}
			
			for(int i = 0; i < potionTime.length; i++) {
				potionTime[i] = 20 + rand.nextInt(20);
			}
			return 0.3F + ((float) Math.sin(Math.toRadians((player.ticksExisted + partialTicks) / potionTime[eye] * 360)) + 1F) / 2F;
			
		}
		
		return 1F;
	}

	private float interpolateRotation(float prevAngle, float nextAngle, float partialTicks) {
		float f = nextAngle - prevAngle;
		while(f < -180) {
			f += 360.0F;
		}
		
		while(f >= 180) {
			f -= 360.0F;
		}
		
		return prevAngle + partialTicks * f;
	}

	private float getHeadPitch(AbstractClientPlayer player, float partialTicks) {
		return interpolateRotation(player.prevRotationPitch, player.rotationPitch, partialTicks);
	}

	private float getHeadYaw(AbstractClientPlayer player, float partialTicks) {
		return interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partialTicks) - interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);
	}

	private float[] getSneakOffset(AbstractClientPlayer player, float partialTicks) {
		if(player.isSneaking()) {
			GlStateManager.translate(0.0f, 0.2f, 0.0F);
			return headJointSneak;
		}
		return headJoint;
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}

}