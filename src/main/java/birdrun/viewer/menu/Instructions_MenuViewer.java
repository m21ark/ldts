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
        String sms = "Instructions... ";
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - 3), sms);

        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width / 2 - "Press ENTER to go back".length() / 2, height / 2), "Press ENTER to go back");
        graphics.putString(new TerminalPosition(width / 2 - "Press Q to exit".length() / 2, height / 2 + 2), "Press Q to exit");
    }


}
