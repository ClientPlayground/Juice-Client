package clientname.gui;


import org.newdawn.slick.opengl.renderer.Renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ModulesGUI extends GuiScreen {
	
	 private static final ResourceLocation modules_resource = new ResourceLocation("modules.png");

	    @Override
	    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {

	        Minecraft.getMinecraft().getTextureManager().bindTexture(modules_resource);

	        Renderer.get().glColor4f(0, 0, 0, 0.9f);

	        int y_position =  (this.height / 2) - (this.height * 686 / 1385 / 2);

	        drawScaledCustomSizeModalRect((this.width / 2) - ((this.height * 1385 / 1385) / 2), y_position,
	                                      0, 216, 1385, 686,
	                                      (this.height * 1385 / 1385), (this.height * 686 / 1385), 1385, 1457);

	        drawScaledCustomSizeModalRect((this.width / 2) - ((this.height * 1295 / 1385) / 2), y_position + (this.height * 112 / 1385),
	                                      0, 905, 1369, 552,
	                                      (this.height * 1295 / 1385), (this.height * 522 / 1385), 1385, 1457);

	        drawScaledCustomSizeModalRect((this.width / 2) - ((this.height * 1295 / 1385) / 2), y_position + (this.height * 31 / 1385),
	                                      0, 0, 622, 84,
	                                      (this.height * 422 / 1385), (this.height * 57 / 1385), 1385, 1457);

	        drawScaledCustomSizeModalRect((this.width / 2) + (this.height * 230 / 1385), y_position + (this.height * 31 / 1385),
	                                      625, 0, 302, 84,
	                                      (this.height * 208 / 1385), (this.height * 57 / 1385), 1385, 1457);

	        drawScaledCustomSizeModalRect((this.width / 2) + (this.height * 449 / 1385), y_position + (this.height * 31 / 1385),
	                                      930, 0, 297, 84,
	                                      (this.height * 199 / 1385), (this.height * 57 / 1385), 1385, 1457);

	        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
	    }
	}