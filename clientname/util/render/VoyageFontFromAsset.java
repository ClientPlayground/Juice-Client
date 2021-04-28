package clientname.util.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public final class VoyageFontFromAsset extends VoyageFont2 {
  protected final VoyageFont2.CharData[] boldItalicChars = new VoyageFont2.CharData[256];
  
  protected final VoyageFont2.CharData[] italicChars = new VoyageFont2.CharData[256];
  
  protected final VoyageFont2.CharData[] boldChars = new VoyageFont2.CharData[256];
  
  private final int[] colorCode = new int[32];
  
  private char COLOR_CODE_START = ' ';
  
  private float[] charWidthFloat = new float[256];
  
  private byte[] glyphWidth = new byte[65536];
  
  private boolean unicodeFlag;
  
  protected DynamicTexture texBold;
  
  protected DynamicTexture texItalic;
  
  protected DynamicTexture texItalicBold;
  
  public VoyageFontFromAsset(ResourceLocation resourceLocation, float size) {
    super(resourceLocation, size);
    setupMinecraftColorCodes();
    setupBoldItalicIDs();
  }
  
  public float drawStringWithShadow(String text, double x, double y, int color, int shadowColor) {
    float shadowWidth = drawString(text, x + 1.0D, y + 1.0D, shadowColor, false);
    return Math.max(shadowWidth, drawString(text, x, y, color, false));
  }
  
  public float drawStringWithShadow(String text, double x, double y, int color) {
    float shadowWidth = drawString(text, x + 1.0D, y + 1.0D, color, true);
    return Math.max(shadowWidth, drawString(text, x, y, color, false));
  }
  
  public float drawString(String text, float x, float y, int color) {
    return drawString(text, x, y, color, false);
  }
  
  public float drawCenteredString(String text, float x, float y, int color) {
    return drawString(text, x - (getStringWidth(text) / 2), y - (getHeight() / 2), color);
  }
  
  public float drawCenteredStringWithShadow(String text, float x, float y, int color) {
    drawString(text, (x - (getStringWidth(text) / 2)) + 0.55D, (y - (getHeight() / 2)) + 0.55D, color, true);
    return drawString(text, x - (getStringWidth(text) / 2), y - (getHeight() / 2), color);
  }
  
  public int getCharWidth(char character) {
    return Math.round(getCharWidthFloat(character));
  }
  
  private float getCharWidthFloat(char p_getCharWidthFloat_1_) {
    if (p_getCharWidthFloat_1_ == ' ')
      return -1.0F; 
    if (p_getCharWidthFloat_1_ != ' ' && p_getCharWidthFloat_1_ != ' ') {
      int i = "\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000\000".indexOf(p_getCharWidthFloat_1_);
      if (p_getCharWidthFloat_1_ > '\000' && i != -1 && !this.unicodeFlag)
        return this.charWidthFloat[i]; 
      if (this.glyphWidth[p_getCharWidthFloat_1_] != 0) {
        int j = this.glyphWidth[p_getCharWidthFloat_1_] >>> 4;
        int k = this.glyphWidth[p_getCharWidthFloat_1_] & 0xF;
        if (k > 7) {
          k = 15;
          j = 0;
        } 
        k++;
        return ((k - j) / 2 + 1);
      } 
      return 0.0F;
    } 
    return this.charWidthFloat[32];
  }
  
  public String trimStringToWidth(String text, int width) {
    return trimStringToWidth(text, width, false);
  }
  
  public String trimStringToWidth(String text, int width, boolean reverse) {
    StringBuilder stringbuilder = new StringBuilder();
    float f = 0.0F;
    int i = reverse ? (text.length() - 1) : 0;
    int j = reverse ? -1 : 1;
    boolean flag = false;
    boolean flag1 = false;
    int k;
    for (k = i; k >= 0 && k < text.length() && f < width; k += j) {
      char c0 = text.charAt(k);
      float f1 = getCharWidthFloat(c0);
      if (flag) {
        flag = false;
        if (c0 != 'l' && c0 != 'L') {
          if (c0 == 'r' || c0 == 'R')
            flag1 = false; 
        } else {
          flag1 = true;
        } 
      } else if (f1 < 0.0F) {
        flag = true;
      } else {
        f += f1;
        if (flag1)
          f++; 
      } 
      if (f > width)
        break; 
      if (reverse) {
        stringbuilder.insert(0, c0);
      } else {
        stringbuilder.append(c0);
      } 
    } 
    return stringbuilder.toString();
  }
  
  public float drawString(String text, double x, double y, int color, boolean shadow) {
    x--;
    y -= 0.5D;
    if (text == null)
      return 0.0F; 
    if (color == 553648127)
      color = 16777215; 
    if ((color & 0xFC000000) == 0)
      color |= 0xFF000000; 
    if (shadow)
      color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000; 
    VoyageFont2.CharData[] currentData = this.charData;
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    boolean bold = false;
    boolean italic = false;
    boolean strike = false;
    boolean underline = false;
    boolean render = true;
    x *= 2.0D;
    y = (y - 5.0D) * 2.0D;
    if (render) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
      int size = text.length();
      GL11.glEnable(3553);
      GL11.glBindTexture(3553, this.tex.getGlTextureId());
      for (int i = 0; i < size; i++) {
        char character = text.charAt(i);
        if (character == this.COLOR_CODE_START && i < size) {
          int colorIndex = 21;
          try {
            colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
          } catch (Exception e) {
            e.printStackTrace();
          } 
          if (colorIndex < 16) {
            bold = false;
            italic = false;
            underline = false;
            strike = false;
            GL11.glBindTexture(3553, this.tex.getGlTextureId());
            currentData = this.charData;
            if (colorIndex < 0 || colorIndex > 15)
              colorIndex = 15; 
            if (shadow)
              colorIndex += 16; 
            int cc = this.colorCode[colorIndex];
            GL11.glColor4f((cc >> 16 & 0xFF) / 255.0F, (cc >> 8 & 0xFF) / 255.0F, (cc & 0xFF) / 255.0F, alpha);
          } else if (colorIndex != 16) {
            if (colorIndex == 17) {
              bold = true;
              if (italic) {
                GL11.glBindTexture(3553, this.texItalicBold.getGlTextureId());
                currentData = this.boldItalicChars;
              } else {
                GL11.glBindTexture(3553, this.texBold.getGlTextureId());
                currentData = this.boldChars;
              } 
            } else if (colorIndex == 18) {
              strike = true;
            } else if (colorIndex == 19) {
              underline = true;
            } else if (colorIndex == 20) {
              italic = true;
              if (bold) {
                GL11.glBindTexture(3553, this.texItalicBold.getGlTextureId());
                currentData = this.boldItalicChars;
              } else {
                GL11.glBindTexture(3553, this.texItalic.getGlTextureId());
                currentData = this.italicChars;
              } 
            } else if (colorIndex == 21) {
              bold = false;
              italic = false;
              underline = false;
              strike = false;
              GL11.glColor4f((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
              GL11.glBindTexture(3553, this.tex.getGlTextureId());
              currentData = this.charData;
            } 
          } 
          i++;
        } else if (character < currentData.length && character >= '\000') {
          GL11.glBegin(4);
          drawChar(currentData, character, (float)x, (float)y + 6.0F);
          GL11.glEnd();
          if (strike)
            drawLine(x, y + ((currentData[character]).height / 2), x + (currentData[character]).width - 8.0D, y + ((currentData[character]).height / 2), 1.0F); 
          if (underline)
            drawLine(x, y + (currentData[character]).height - 2.0D, x + (currentData[character]).width - 8.0D, y + (currentData[character]).height - 2.0D, 1.0F); 
          x += ((currentData[character]).width - 8 + this.charOffset);
        } 
      } 
    } 
    GL11.glHint(3155, 4352);
    GL11.glPopMatrix();
    return (float)x / 2.0F;
  }
  
  public int getStringWidth(String text) {
    if (text == null)
      return 0; 
    int width = 0;
    VoyageFont2.CharData[] currentData = this.charData;
    boolean bold = false;
    boolean italic = false;
    int size = text.length();
    for (int i = 0; i < size; i++) {
      char character = text.charAt(i);
      if (character == this.COLOR_CODE_START && i < size) {
        int colorIndex = "0123456789abcdefklmnor".indexOf(character);
        if (colorIndex < 16) {
          bold = false;
          italic = false;
        } else if (colorIndex == 17) {
          bold = true;
          if (italic) {
            currentData = this.boldItalicChars;
          } else {
            currentData = this.boldChars;
          } 
        } else if (colorIndex == 20) {
          italic = true;
          if (bold) {
            currentData = this.boldItalicChars;
          } else {
            currentData = this.italicChars;
          } 
        } else if (colorIndex == 21) {
          bold = false;
          italic = false;
          currentData = this.charData;
        } 
        i++;
      } else if (character < currentData.length && character >= '\000') {
        width += (currentData[character]).width - 8 + this.charOffset;
      } 
    } 
    return width / 2;
  }
  
  public void setAntiAlias(boolean antiAlias) {
    super.setAntiAlias(antiAlias);
    setupBoldItalicIDs();
  }
  
  public void setFractionalMetrics(boolean fractionalMetrics) {
    super.setFractionalMetrics(fractionalMetrics);
    setupBoldItalicIDs();
  }
  
  private void setupBoldItalicIDs() {
    this.texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
    this.texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
    this.texItalicBold = setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
  }
  
  private void drawLine(double x, double y, double x1, double y1, float width) {
    GL11.glDisable(3553);
    GL11.glLineWidth(width);
    GL11.glBegin(1);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x1, y1);
    GL11.glEnd();
    GL11.glEnable(3553);
  }
  
  public List<String> wrapWords(String text, double width) {
    List<String> finalWords = new ArrayList<>();
    if (getStringWidth(text) > width) {
      String[] words = text.split(" ");
      String currentWord = "";
      char lastColorCode = Character.MAX_VALUE;
      for (String word : words) {
        for (int i = 0; i < (word.toCharArray()).length; i++) {
          char c = word.toCharArray()[i];
          if (c == this.COLOR_CODE_START && i < (word.toCharArray()).length - 1)
            lastColorCode = word.toCharArray()[i + 1]; 
        } 
        if (getStringWidth(currentWord + word + " ") < width) {
          currentWord = currentWord + word + " ";
        } else {
          finalWords.add(currentWord);
          currentWord = (this.COLOR_CODE_START + lastColorCode) + word + " ";
        } 
      } 
      if (currentWord.length() > 0)
        if (getStringWidth(currentWord) < width) {
          finalWords.add((this.COLOR_CODE_START + lastColorCode) + currentWord + " ");
          currentWord = "";
        } else {
          for (String s : formatString(currentWord, width))
            finalWords.add(s); 
        }  
    } else {
      finalWords.add(text);
    } 
    return finalWords;
  }
  
  public List<String> formatString(String string, double width) {
    List<String> finalWords = new ArrayList<>();
    String currentWord = "";
    char lastColorCode = Character.MAX_VALUE;
    char[] chars = string.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == this.COLOR_CODE_START && i < chars.length - 1)
        lastColorCode = chars[i + 1]; 
      if (getStringWidth(currentWord + c) < width) {
        currentWord = currentWord + c;
      } else {
        finalWords.add(currentWord);
        currentWord = (this.COLOR_CODE_START + lastColorCode) + String.valueOf(c);
      } 
    } 
    if (currentWord.length() > 0)
      finalWords.add(currentWord); 
    return finalWords;
  }
  
  private void setupMinecraftColorCodes() {
    for (int index = 0; index < 32; index++) {
      int alpha = (index >> 3 & 0x1) * 85;
      int red = (index >> 2 & 0x1) * 170 + alpha;
      int green = (index >> 1 & 0x1) * 170 + alpha;
      int blue = (index & 0x1) * 170 + alpha;
      if (index == 6)
        red += 85; 
      if (index >= 16) {
        red /= 4;
        green /= 4;
        blue /= 4;
      } 
      this.colorCode[index] = (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    } 
  }
}