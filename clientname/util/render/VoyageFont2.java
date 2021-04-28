package clientname.util.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class VoyageFont2 {
  protected CharData[] charData;
  
  protected DynamicTexture tex;
  
  protected Font font;
  
  protected boolean antiAlias;
  
  protected boolean fractionalMetrics;
  
  protected int fontHeight;
  
  protected int charOffset;
  
  private float imgSize;
  
  public VoyageFont2(ResourceLocation resourceLocation, float size) {
    Font tmp;
    this.charData = new CharData[256];
    this.fontHeight = -1;
    this.charOffset = 0;
    this.imgSize = 1048.0F;
    try {
      InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
      tmp = Font.createFont(0, is).deriveFont(size);
    } catch (IOException|java.awt.FontFormatException e) {
      tmp = new Font("Arial", 0, (int)size);
      e.printStackTrace();
    } 
    this.font = tmp;
    this.antiAlias = true;
    this.fractionalMetrics = true;
    this.tex = setupTexture(this.font, true, true, this.charData);
  }
  
  protected DynamicTexture setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
    BufferedImage img = generateFontImage(font, antiAlias, fractionalMetrics, chars);
    try {
      return new DynamicTexture(img);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  private BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
    int imgSize = (int)this.imgSize;
    BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, 2);
    Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
    g.setFont(font);
    g.setColor(new Color(255, 255, 255, 0));
    g.fillRect(0, 0, imgSize, imgSize);
    g.setColor(Color.WHITE);
    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
    FontMetrics fontMetrics = g.getFontMetrics();
    int charHeight = 0;
    int positionX = 0;
    int positionY = 1;
    for (int i = 0; i < chars.length; i++) {
      char ch = (char)i;
      CharData charData = new CharData();
      Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
      charData.width = (dimensions.getBounds()).width + 8;
      charData.height = (dimensions.getBounds()).height;
      if (positionX + charData.width >= imgSize) {
        positionX = 0;
        positionY += charHeight;
        charHeight = 0;
      } 
      if (charData.height > charHeight)
        charHeight = charData.height; 
      charData.storedX = positionX;
      charData.storedY = positionY;
      if (charData.height > this.fontHeight)
        this.fontHeight = charData.height; 
      chars[i] = charData;
      g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());
      positionX += charData.width;
    } 
    return bufferedImage;
  }
  
  protected void drawChar(CharData[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
    try {
      drawQuad(x, y, (chars[c]).width, (chars[c]).height, (chars[c]).storedX, (chars[c]).storedY, (chars[c]).width, (chars[c]).height);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
    float renderSRCX = srcX / this.imgSize;
    float renderSRCY = srcY / this.imgSize;
    float renderSRCWidth = srcWidth / this.imgSize;
    float renderSRCHeight = srcHeight / this.imgSize;
    GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
    GL11.glVertex2d((x + width), y);
    GL11.glTexCoord2f(renderSRCX, renderSRCY);
    GL11.glVertex2d(x, y);
    GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
    GL11.glVertex2d(x, (y + height));
    GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
    GL11.glVertex2d(x, (y + height));
    GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
    GL11.glVertex2d((x + width), (y + height));
    GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
    GL11.glVertex2d((x + width), y);
  }
  
  public int getHeight() {
    return (this.fontHeight - 8) / 2;
  }
  
  public int getStringWidth(String text) {
    int width = 0;
    for (char c : text.toCharArray()) {
      if (c < this.charData.length)
        width += (this.charData[c]).width - 8 + this.charOffset; 
    } 
    return width / 2;
  }
  
  public boolean isAntiAlias() {
    return this.antiAlias;
  }
  
  public void setAntiAlias(boolean antiAlias) {
    if (this.antiAlias != antiAlias) {
      this.antiAlias = antiAlias;
      this.tex = setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
    } 
  }
  
  public boolean isFractionalMetrics() {
    return this.fractionalMetrics;
  }
  
  public void setFractionalMetrics(boolean fractionalMetrics) {
    if (this.fractionalMetrics != fractionalMetrics) {
      this.fractionalMetrics = fractionalMetrics;
      this.tex = setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
    } 
  }
  
  public Font getFont() {
    return this.font;
  }
  
  public void setFont(Font font) {
    this.font = font;
    this.tex = setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
  }
  
  protected static class CharData {
    public int width;
    
    public int height;
    
    public int storedX;
    
    public int storedY;
  }
}
