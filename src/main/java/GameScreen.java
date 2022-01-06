import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameScreen {

    private final int width;
    private final int height;
    private final String bgColor;
    private final String textColor;

    public GameScreen(int width, int height, String bgColor, String textColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    public void drawLoadingScreen(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        String sms = "Welcome to Run Bird run! ";
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - 1), sms);
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2), "Press q to start...");
    }

    public void drawDeathScreen(TextGraphics graphics, int playerCoinCount) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        String sms = "You died!  Your score was " + playerCoinCount + " .";
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - 1), sms);
        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2), "Press q to exit...");
    }


}
