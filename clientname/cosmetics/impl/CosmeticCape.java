package clientname.cosmetics.impl;

import clientname.cosmetics.CosmeticBase;
import clientname.cosmetics.CosmeticController;
import clientname.cosmetics.CosmeticModelBase;
import clientname.http.gsonobjs.ObjectUserCosmetics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CosmeticCape extends CosmeticBase {
	
	private final ModelCape modelCape;
	
	private static final ResourceLocation MONKIES = new ResourceLocation("clientname/123.png");
	private static final ResourceLocation FREELOADERS = new ResourceLocation("clientname/FreeloadersCape.png");
	private static final ResourceLocation DUST = new ResourceLocation("clientname/DustCape.png");
	private static final ResourceLocation DITTO = new ResourceLocation("clientname/DittoCape.png");
	private static final ResourceLocation JUICE = new ResourceLocation("clientname/JuiceCape.png");
	private static final ResourceLocation ZWIZZY = new ResourceLocation("clientname/zwizzycape.png");
	private static final ResourceLocation TRIVAGO = new ResourceLocation("clientname/trivagooo.png");
	private static final ResourceLocation SWIFT = new ResourceLocation("clientname/swift.png");
	private static final ResourceLocation SOKLA = new ResourceLocation("clientname/giveawaycape.png");
	
	public CosmeticCape(RenderPlayer renderPlayer) {
		super(renderPlayer);
		modelCape = new ModelCape(renderPlayer);
	}

	@Override
	public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmmount, float partialTicks,
			float ageInTicks, float headYaw, float headPitch, float scale) {
		
		if (player.hasPlayerInfo() && !player.isInvisible() && player.isWearing(EnumPlayerModelParts.CAPE))
        {
        	if(CosmeticController.shouldRenderCape(player)) {
        		if(CosmeticController.monkiesCape(player) == 1) {
        			this.playerRenderer.bindTexture(MONKIES);
        		}
        		else if(CosmeticController.monkiesCape(player) == 2) {
        			this.playerRenderer.bindTexture(FREELOADERS);
        		}
        		else if(CosmeticController.monkiesCape(player) == 3) {
        			this.playerRenderer.bindTexture(DUST);
        		}
        		else if(CosmeticController.monkiesCape(player) == 4) {
        			this.playerRenderer.bindTexture(DITTO);
        		}
        		else if(CosmeticController.monkiesCape(player) == 5) {
        			this.playerRenderer.bindTexture(JUICE);
        		}
        		else if(CosmeticController.monkiesCape(player) == 6) {
        			this.playerRenderer.bindTexture(TRIVAGO);
        		}
        		else if(CosmeticController.monkiesCape(player) == 7) {
        			this.playerRenderer.bindTexture(SWIFT);
        		}
        		else if(CosmeticController.monkiesCape(player) == 8) {
        			this.playerRenderer.bindTexture(SOKLA);
        		}
        		else if(CosmeticController.monkiesCape(player) == 900) {
        			this.playerRenderer.bindTexture(ZWIZZY);
        		}
        		
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, 0.125F);
            double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
            double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
            double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
            float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
            double d3 = (double)MathHelper.sin(f * (float)Math.PI / 180.0F);
            double d4 = (double)(-MathHelper.cos(f * (float)Math.PI / 180.0F));
            float f1 = (float)d1 * 10.0F;
            f1 = MathHelper.clamp_float(f1, -6.0F, 32.0F);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f2 > 165.0F)
            {
                f2 = 165.0F;
            }

            float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
            f1 = f1 + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

            if (player.isSneaking())
            {
                f1 += 25.0F;
                GlStateManager.translate(0.0F, 0.142F, -0.0178F);
            }

            GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.playerRenderer.getMainModel().renderCape(0.0625F);
            GlStateManager.popMatrix();
        }
        }
		
	}
	
	public static boolean ownsCosmetic() {
		int watermellon = 6;
		if(watermellon  == 6) return true;
		else return false;
	}
	private class ModelCape extends CosmeticModelBase {

		private ModelRenderer rim;
		private ModelRenderer tip;
		
		public ModelCape(RenderPlayer player) {
			super(player);
			rim = new ModelRenderer(playerModel, 0 , 0);
			rim.addBox(-5.5F, -9F, -5.5F, 11, 2, 11);
			tip = new ModelRenderer(playerModel, 0, 13);
			tip.addBox(-3.5F, -17F, -3.5F, 7, 8, 7);
		}
		
		@Override
		public void render(Entity entityIn, float limbSwing, float limbSwingAmmount, float ageInTicks, float headYaw, float headPitch, float scale) {
			
			rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			rim.rotationPointX = 0.0F;
			rim.rotationPointY = 0.0F;
			rim.render(scale);
			
			tip.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			tip.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			tip.rotationPointX = 0.0F;
			tip.rotationPointY = 0.0F;
			tip.render(scale);
			
		}
		
	}
	public boolean shouldCombineTextures()
    {
        return false;
    }

    public void renderLayer(EntityLivingBase player, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale)
    {
        this.renderLayer((AbstractClientPlayer)player, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);
    }
}