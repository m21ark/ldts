package birdrun.viewer.menu;

import birdrun.model.Dimensions;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Death_MenuViewer extends MenuViewer {

    public Death_MenuViewer(Dimensions dimensions, String bgColor, String textColor) {
        super(dimensions, bgColor, textColor);
    }

    public void draw(TextGraphics graphics, int playerCoinCount) {

        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        writeSMS(graphics, " You died!", 5);
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        writeSMS(graphics, "Your score was " + playerCoinCount, 1);
        writeSMS(graphics, "Press ENTER  to play again ", -3);
        writeSMS(graphics, "Press Q to exit ", -5);

    }


}
