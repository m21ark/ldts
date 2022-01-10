package birdrun.viewer;

import birdrun.model.Dimensions;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuViewer {

    private final int width;
    private final int height;
    private final String bgColor;
    private final String textColor;

    public MenuViewer(Dimensions dimensions, String bgColor, String textColor) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    public void drawLoadingScreen(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        String sms = "Welcome to Run Bird run! ";
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - 3), sms);

        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width / 2 - "Press S to start".length() / 2, height / 2), "Press S to start");
        graphics.putString(new TerminalPosition(width / 2 - "Press Q to exit".length() / 2, height / 2 + 2), "Press Q to exit");
    }

    public void drawDeathScreen(TextGraphics graphics, int playerCoinCount) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        String sms = "Your score was " + playerCoinCount;

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(width / 2 - "You died!".length() / 2, height / 2 - 3), "You died!");
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - 1), sms);
        graphics.putString(new TerminalPosition(width / 2 - "Press R  to play again".length() / 2, height / 2 + 3), "Press R  to play again");
        graphics.putString(new TerminalPosition(width / 2 - "Press Q to exit".length() / 2, height / 2 + 5), "Press Q to exit");

    }


}
