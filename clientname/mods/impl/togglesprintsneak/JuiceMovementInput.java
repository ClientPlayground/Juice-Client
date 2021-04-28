package clientname.mods.impl.togglesprintsneak;

import java.text.DecimalFormat;

import clientname.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;

public class JuiceMovementInput extends MovementInput {

	private boolean sprint = false;
	private GameSettings gameSettings;
	private int sneakWasPressed = 0;
	private int sprintWasPressed = 0;
	private EntityPlayerSP player;
	private float originalFlySpeed = -1.0F;
	private float boostedFlySpeed = 1;
	private Minecraft mc;
	
	public JuiceMovementInput(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.mc = Minecraft.getMinecraft();
		
	}
	@Override
	public void updatePlayerMoveState() {
		
		player = mc.thePlayer;
		moveStrafe = 0.0F;
		moveForward = 0.0F;
		
		if(gameSettings.keyBindForward.isKeyDown()) {
			moveForward++;
		}
		
		if(gameSettings.keyBindBack.isKeyDown()) {
			moveForward--;
		}
		
		if(gameSettings.keyBindLeft.isKeyDown()) {
			moveStrafe++;
		}
		
		if(gameSettings.keyBindRight.isKeyDown()) {
			moveStrafe--;		
		}
		
		jump = gameSettings.keyBindJump.isKeyDown();

		sneak = gameSettings.keyBindSneak.isKeyDown();
		
		
		if(sneak) {
			moveStrafe *= 0.3F;
			moveForward *= 0.3F;
		}
		
		if(ModInstances.getModToggleSprintSneak().isEnabled()) {
			
			if(gameSettings.keyBindSprint.isKeyDown()) {
				if(sprintWasPressed == 0) {
					
					if(sprint) {
						sprintWasPressed = -1;
					}
					else if(player.capabilities.isFlying) {
						sprintWasPressed = ModInstances.getModToggleSprintSneak().keyHoldTicks + 1;
					}
					else {
						sprintWasPressed = 1;
					}
					sprint = !sprint;
				}
				else if(sprintWasPressed > 0) {
					sprintWasPressed++;
				}
			}
			else {
				if((ModInstances.getModToggleSprintSneak().keyHoldTicks > 0) && (sprintWasPressed > ModInstances.getModToggleSprintSneak().keyHoldTicks)) {
					sprint = false;
				}
				sprintWasPressed = 0;
			}
			
		}
		else {
			sprint = false;
		}
		
		if(sprint && moveForward == 1.0F && player.onGround && !player.isUsingItem() && !player.isPotionActive(Potion.blindness)) {
			player.setSprinting(true);
		}
		
		if(ModInstances.getModToggleSprintSneak().flyBoost && player.capabilities.isCreativeMode && player.capabilities.isFlying && (mc.getRenderViewEntity() == player) && sprint) {
			
			if(originalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed) {
				originalFlySpeed = this.player.capabilities.getFlySpeed();
			}
			
			boostedFlySpeed = originalFlySpeed * ModInstances.getModToggleSprintSneak().flyBoostFactor;
			player.capabilities.setFlySpeed(boostedFlySpeed);
			
			if(sneak) {
				player.motionY -= 0.15D * (double)(ModInstances.getModToggleSprintSneak().flyBoostFactor - 1.0F);
			}
			
			if(jump) {
				player.motionY += 0.15D * (double)(ModInstances.getModToggleSprintSneak().flyBoostFactor - 1.0F);
			}
			
		}
		else {
			if(player.capabilities.getFlySpeed() == boostedFlySpeed) {
				this.player.capabilities.setFlySpeed(originalFlySpeed);
			}
			originalFlySpeed = -1.0F;
		}
		
	}
	
	private static final DecimalFormat df = new DecimalFormat("#.0");
	public String getDisplayText() {
		
		String displayText = "";
		
		boolean isFlying = mc.thePlayer.capabilities.isFlying;
		boolean isRiding = mc.thePlayer.isRiding();
		boolean isHoldingSneak = gameSettings.keyBindSneak.isKeyDown();
		boolean isHoldingSprint = gameSettings.keyBindSprint.isKeyDown();
		
		if(isFlying) {
			if(originalFlySpeed > 0.0F) {
				displayText += "[Flying (" + df.format(boostedFlySpeed / originalFlySpeed) + "x Boost)]  ";
			} else {
				displayText += "[Flying]  ";
			}
		}
		
		if(isRiding) {
			displayText += "[Riding]  ";
		} 
		
		
		else if(sprint && !isFlying && !isRiding ) {
			if(isHoldingSprint) {
				displayText += "[Sprinting (Key Held)]  ";
			} else {
				displayText += "[Sprinting (Key Toggled)]  ";
			}
		}
		
		
		return displayText.trim();
		
	}
	
}
