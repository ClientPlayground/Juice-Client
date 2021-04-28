package clientname.cosmetics.impl.eyes;


import java.util.Random;

import clientname.cosmetics.CosmeticController;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.MathHelper;

public class EyePhysics {

	private final AbstractClientPlayer player;
	private final Random rand;
	
	private boolean shouldUpdate = true;
	private long lastUpdate;
	
	private double motionX;
	private double motionY;
	private double motionZ;
	
	public EyeInfo[] eyes;
	
	public class EyeInfo {
		
		private float rotationYaw;
		private float rotationPitch;
		
		public float prevDeltaX;
		public float prevDeltaY;
		public float deltaX;
		public float deltaY;
		
		private float momentumX;
		private float momentumY;
		
		public EyeInfo() {
			prevDeltaY = deltaY = -1f;
		}
		
		public void update(int eve, EyePhysics physics, double motionX, double motionY, double motionZ) {
			
			float prevRotationYaw = rotationYaw;
			float prevRotationPitch = rotationPitch;
			
			rotationYaw = physics.player.prevRotationYaw;
			rotationPitch = physics.player.prevRotationPitch;
			
			prevDeltaX = deltaX;
			prevDeltaY = deltaY;
			
			float yawDiff = rotationYaw - prevRotationYaw;
			float pitchDiff = rotationPitch - prevRotationPitch;
			
			momentumY += motionY * 1.5F + (motionX + motionZ) * rand.nextGaussian() * 0.75F + (pitchDiff / 45F) + (yawDiff / 180F);
			momentumX -= (motionX + motionZ) * rand.nextGaussian() * 0.4F + (yawDiff / 45F);
			
			float momentumLoss = 0.9F;
			float newDeltaX = deltaX + momentumX;
			float newDeltaY = deltaY + momentumY;
			
			if(newDeltaX < -1F || newDeltaX > 1F) {
				
				float newMo = momentumX * -momentumLoss;
				float randomFloat = 0.8F + rand.nextFloat() * 0.2F;
				momentumX = newMo * randomFloat;
				momentumY += newMo * randomFloat * (rand.nextFloat() > 0.5F ? 1F : -1F);
				
			}
			
			if(newDeltaY < -1F || newDeltaY > 1F) {
				float newMo = momentumY * -momentumLoss;
				float randomFloat = 0.8F + rand.nextFloat() * 0.2F;
				momentumX = newMo * randomFloat;
				momentumY += newMo * randomFloat * (rand.nextFloat() > 0.5F ? 1F : -1F);
			}
			else {
				momentumY -= MathHelper.clamp_float(1F + deltaY, 0F, 0.1999F);
			}
			
			momentumX *= 0.95F;
			deltaX *= 0.95F;
			
			if(Math.abs(momentumX) < 0.03F) {
				momentumX = 0.0F;
			}
			
			if(Math.abs(deltaX) < 0.03F) {
				deltaX = 0.0F;
			}
			
			float maxMomentum = 1.3f;
			
			momentumX = MathHelper.clamp_float(momentumX, -maxMomentum, maxMomentum);
			momentumY = MathHelper.clamp_float(momentumY, -maxMomentum, maxMomentum);
			
			deltaX += momentumX;
			deltaY += momentumY;
			
			deltaX = MathHelper.clamp_float(deltaX, -1F, 1F);
			deltaY = MathHelper.clamp_float(deltaY, -1F, 1F);
			
		}
		
	}
	
	public EyePhysics(AbstractClientPlayer player) {
		this.player = player;
		this.rand = new Random(Math.abs(player.hashCode()) * 8134);
		this.eyes = new EyeInfo[2];
		for(int i = 0; i < eyes.length; i++) {
			this.eyes[i] = new EyeInfo();
		}
		update();
	}

	public void update() {
		
		if(!shouldUpdate || !CosmeticController.shouldRenderEyes(player)) {
			return;
		}
		
		shouldUpdate = false;
		
		motionX = player.posX - player.prevPosX;
		motionY = player.posY - player.prevPosY;
		motionZ = player.posZ - player.prevPosZ;
		
		for(int i = 0; i < eyes.length; i++) {
			eyes[i].update(i, this, motionX, motionY, motionZ);
		}
		
	}
	
	public void requireUpdate() {
		shouldUpdate = true;
		lastUpdate = player.worldObj.getWorldTime();
	}
	
	public long getLastUpdate() {
		return lastUpdate;
	}
	
	public AbstractClientPlayer getPlayer() {
		return player;
	}
	
}
