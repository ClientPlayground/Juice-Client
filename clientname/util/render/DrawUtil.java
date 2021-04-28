package clientname.util.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

public class DrawUtil extends GuiScreen {
	
	public static DrawUtil instance = new DrawUtil();
  public static void drawRoundedRect(double x, double y, double x1, double y1, double radius, int color) {
    GL11.glPushAttrib(0);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    x *= 2.0D;
    y *= 2.0D;
    x1 *= 2.0D;
    y1 *= 2.0D;
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    setColor(color);
    GL11.glEnable(2848);
    GL11.glBegin(9);
    int i;
    for (i = 0; i <= 90; i += 3)
      GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
    for (i = 90; i <= 180; i += 3)
      GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
    for (i = 0; i <= 90; i += 3)
      GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
    for (i = 90; i <= 180; i += 3)
      GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
    GL11.glEnd();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glDisable(2848);
    GL11.glDisable(3042);
    GL11.glEnable(3553);
    GL11.glScaled(2.0D, 2.0D, 2.0D);
    GL11.glPopAttrib();
  }
  
  public static void drawBorderedRoundedRect(float x, float y, float x1, float y1, float borderSize, int borderC, int insideC) {
    drawRoundedRect(x, y, x1, y1, borderSize, borderC);
    drawRoundedRect((x + 0.5F), (y + 0.5F), (x1 - 0.5F), (y1 - 0.5F), borderSize, insideC);
  }
  
  public static void drawBorderedRoundedRect(float x, float y, float x1, float y1, float radius, float borderSize, int borderC, int insideC) {
    drawRoundedRect(x, y, x1, y1, radius, borderC);
    drawRoundedRect((x + borderSize), (y + borderSize), (x1 - borderSize), (y1 - borderSize), radius, insideC);
  }
  
  public static void setColor(int color) {
    float a = (color >> 24 & 0xFF) / 255.0F;
    float r = (color >> 16 & 0xFF) / 255.0F;
    float g = (color >> 8 & 0xFF) / 255.0F;
    float b = (color & 0xFF) / 255.0F;
    GL11.glColor4f(r, g, b, a);
  }
  
  public static void drawEntityOnScreen(int posX, int posY, int scale, float rotation, EntityLivingBase ent)
  {
      float rY = ent.rotationYaw % 360;
      float rYH = ent.rotationYawHead % 360;
      float rYO = ent.renderYawOffset;
      ent.rotationYawHead = rotation + rYH - rYO;
      ent.rotationYaw = rotation;
      ent.renderYawOffset = rotation;
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, 50.0F);
      GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
      rendermanager.setPlayerViewY(180.0F);
      rendermanager.setRenderShadow(false);
      rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      rendermanager.setRenderShadow(true);
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      ent.rotationYaw = rY;
      ent.rotationYawHead = rYH;
      ent.renderYawOffset = rYO;
  }
  
  public void drawPlayerHead(int x, int y, int width) {
      GlStateManager.pushMatrix();
      float scale = width / 32;
      GlStateManager.scale(scale, scale, scale);
      Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().thePlayer.getLocationSkin());
      GL11.glEnable(GL11.GL_BLEND);
      this.drawTexturedModalRect(x / scale, y / scale, 32, 32, 32, 32);
      GL11.glDisable(GL11.GL_BLEND);
      GlStateManager.popMatrix();
  }
}
