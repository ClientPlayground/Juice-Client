package clientname.gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.portable.ApplicationException;

import clientname.Client;
import clientname.http.HTTPFunctions;
import clientname.http.HWID;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class GuiWhitelisted extends GuiScreen {

	private IChatComponent[] message;
	private int messageLengthTimesFontHeight;
	private int secondsLeft = 0;

	private static Timer timer = new Timer();

	private GuiButton refreshButton;

	public GuiWhitelisted()
	{

		message = new IChatComponent[] {
				new ChatComponentText("Hi,"),
				new ChatComponentText(""),
				new ChatComponentText("Thank you for using Juice Client!"),
				new ChatComponentText(""),
				new ChatComponentText("The client is currently whitelisted for Beta Testing."),
				new ChatComponentText(""),
				new ChatComponentText("Below you can find your HWID, if you where asked for this, "),
				new ChatComponentText("please provide it to a juice client staff member."),
				new ChatComponentText(""),
				new ChatComponentText(HWID.get()).setChatStyle(
						new ChatStyle().setColor(EnumChatFormatting.GOLD)
						.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to copy your HWID").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN))))
						.setChatClickEvent(new ClickEvent(ClickEvent.Action.COPY_CLIPBOARD, HWID.get()))
						),
				new ChatComponentText(""),
		};

	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
	}

	@Override
	public void initGui()
	{
		this.buttonList.clear();

		this.messageLengthTimesFontHeight = this.message.length * this.fontRendererObj.FONT_HEIGHT;
		this.buttonList.add(new GuiButton(0, width / 2 - 100, height - 25, 98, 20, EnumChatFormatting.RED + "Close"));
		this.buttonList.add(refreshButton = new GuiButton(1, width / 2 + 2, height - 25, 98, 20, "Refresh"));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.id == 0)
		{
			mc.shutdown();
		}
		if (button.id == 1 && secondsLeft == 0)
		{

			boolean isWhitelisted = HTTPFunctions.isWhitelisted();

			if(!isWhitelisted) {

				secondsLeft = 6;
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						secondsLeft--;
						if(secondsLeft == 0) {
							refreshButton.displayString = "Refresh Again";
							refreshButton.enabled = true;
							cancel();
							return;
						}

						refreshButton.enabled = false;
						refreshButton.displayString = EnumChatFormatting.AQUA + "" + secondsLeft + "...";
					}
				}, 0, 1000);
			}
			else {
				Client.getInstance().isWhitelisted = true;
				mc.displayGuiScreen(null);
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		ScaledResolution scaledRes = new ScaledResolution(this.mc, mouseY, mouseY);
    	this.mc.getTextureManager().bindTexture(new ResourceLocation("clientname/whitelist.png"));
    	Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), scaledRes.getScaledWidth(), scaledRes.getScaledHeight());
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int w = 274;
        int j = this.width / 2 - w / 2;
        int k = 30;
        this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        

		int i = this.height / 2 - this.messageLengthTimesFontHeight / 2;


		for (IChatComponent s : this.message)
		{
			this.drawCenteredString(this.fontRendererObj, s.getFormattedText(), this.width / 2, i, 16777215);
			i += this.fontRendererObj.FONT_HEIGHT;


		}

		handleComponentHover(findChatComponent(mouseX, mouseY), mouseX, mouseY);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}


	private IChatComponent findChatComponentLine(int mouseY)
	{
		int i = this.height / 2 - this.messageLengthTimesFontHeight / 2;

		for (IChatComponent s : this.message)
		{
			int yTop = i;
			int yBottom = i + this.fontRendererObj.FONT_HEIGHT;
			if (mouseY >= yTop && mouseY < yBottom) {
				return s;
			}
			i += this.fontRendererObj.FONT_HEIGHT;
		}

		return null;
	}

	private IChatComponent findChatComponent(int mouseX, int mouseY) {

		IChatComponent s = findChatComponentLine(mouseY);

		if (s == null || !(s instanceof ChatComponentText)) {
			return null;
		}

		int stringWidth = this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)s).getChatComponentText_TextValue(), false));
		int xLeft = this.width / 2 - stringWidth / 2;
		int xRight = this.width / 2 + stringWidth / 2;
		if (mouseX >= xLeft && mouseX < xRight) {
			return s;
		}

		return null;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (mouseButton == 0)
		{
			IChatComponent ichatcomponent = findChatComponent(mouseX, mouseY);

			if (this.handleComponentClick(ichatcomponent))
			{
				return;
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}