package clientname.gui.hud.elements;

import clientname.Client;
import net.minecraft.client.gui.Gui;

public class Element {

    void drawHollowRect(int x, int y, int w, int h, int colour) {
        this.drawHorizontalLine(x, x + w, y, colour);
        this.drawHorizontalLine(x, x + w, y + h, colour);
        this.drawVerticalLine(x, y + h, y, colour);
        this.drawVerticalLine(x + w, y + h, y, colour);
        Client.getInstance().cleanGL();
    }

    private void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        Gui.drawRect(startX, y, endX + 1, y + 1, color);
    }

    private void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        Gui.drawRect(x, startY + 1, x + 1, endY, color);
    }

    public void draw() {
        return;
    }

    public void interact(int mouseX, int mouseY) {
        return;
    }

    public void hover() {
        return;
    }

    public void unHover() {
        return;
    }

    public boolean mouseOverlap(int mouseX, int mouseY) {
        return false;
    }

    /** Values **/

    public boolean getBool()    { return false; }
    public int getInt()         { return 0;     }
    public float getFloat()     { return 0;     }
    public String getString()   { return null;  }

}
