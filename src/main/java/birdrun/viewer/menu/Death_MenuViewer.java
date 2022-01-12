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

        String sms = "Your score was " + playerCoinCount;

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(width / 2 - "You died!".length() / 2, height / 2 - 3), "You died!");
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - 1), sms);
        graphics.putString(new TerminalPosition(width / 2 - "Press ENTER  to play again".length() / 2, height / 2 + 3), "Press ENTER  to play again");
        graphics.putString(new TerminalPosition(width / 2 - "Press Q to exit".length() / 2, height / 2 + 5), "Press Q to exit");

    }


}
