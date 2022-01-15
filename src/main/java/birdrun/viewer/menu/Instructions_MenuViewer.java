package birdrun.viewer.menu;

import birdrun.model.Dimensions;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Instructions_MenuViewer extends MenuViewer {

    public Instructions_MenuViewer(Dimensions dimensions, String bgColor, String textColor) {
        super(dimensions, bgColor, textColor);
    }


    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        writeSMS(graphics, "Instructions", 12);

        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        writeSMS(graphics, "You can use either A W D  or", 8);
        writeSMS(graphics, "the arrow keys to move", 6);
        writeSMS(graphics, "Avoid the falling boxes ! ", 0);
        writeSMS(graphics, "Catch all  coins you can !", -2);
        writeSMS(graphics, "Press ENTER to go back", -8);
        writeSMS(graphics, "Press Q to exit", -10);
    }


}
