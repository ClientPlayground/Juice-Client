package clientname.gui.hud.elements;

	import clientname.Client;
	import net.minecraft.client.gui.Gui;


	public class ClientToggle extends Element {

		private int x;
		private int y;
 		private int width;
 		private int height;
		private boolean position;

	    private int anim;

	    public ClientToggle(int x, int y, int width, int height, boolean position) {

	        this.x = x;
	        this.y = y;

	        this.width = width;
	        this.height = height;

	        this.position = position;

	        this.anim = 0;

	    }

	    @Override
	    public boolean mouseOverlap(int mouseX, int mouseY) {

	        if(mouseX >= x - (width / 2) && mouseX <= x + (width / 2)) {
	            if(mouseY >= y - (height / 2) && mouseY <= y + (height / 2)) {
	                return true;
	            }
	        }

	        return false;

	    }

	    @Override
	    public void draw() {

	        if(position && anim < width) anim++;
	        else if(!position && anim > 0) anim -= 3;

	        Gui.drawRect(
	                x - (width / 2),
	                y - (height / 2),
	                x - (width / 2) + anim,
	                y + (height / 2),
	                Client.getInstance().getColour()
	        );

	        int toggleX = anim;

	        Gui.drawRect(x - (width / 2) - (width / 10) + toggleX, y - (height / 2) - (width / 10), x - 10 + toggleX, y + (height / 2) + (width / 10), -1);

	        drawHollowRect(x - (width / 2), y - (height / 2), width, height, -1);

	    }

	    @Override
	    public void interact(int mouseX, int mouseY) {
	        position = !position;
	    }

	    @Override
	    public boolean getBool() {
	        return position;
	    }
}
