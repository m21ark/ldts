package birdrun.viewer.menu;

import birdrun.model.Dimensions;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pause_MenuViewer extends MenuViewer {

    public Pause_MenuViewer(Dimensions dimensions, String bgColor, String textColor) {
        super(dimensions, bgColor, textColor);
    }

    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        writeSMS(graphics, "Paused ", 3);
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        writeSMS(graphics, "Press ENTER to continue ", 0);
        writeSMS(graphics, "Press Q to exit ", -2);

    }

}
