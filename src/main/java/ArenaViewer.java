import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ArenaViewer {

    private final int width;
    private final int height;
    private final String bgColor;
    private final String textColor;

    public ArenaViewer(int width, int height, String bgColor, String textColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    private void drawMatrix(TextGraphics graphics, Matrix matrix) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                Element e = matrix.getPos(x, y);
                if (e.getChar() != ' ') e.draw(graphics);
            }
    }


    public void drawGame(TextGraphics graphics, int playerCoinCount, int playerHP, Matrix matrix) {
        //Set screen
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');

        //Draw updated matrix
        drawMatrix(graphics, matrix);

        //draw picked up coins
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(width - 12, 1), "Score: " + playerCoinCount);

        //draw lifePoints
        graphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        graphics.putString(new TerminalPosition(2, 1), "HP: " + playerHP);
    }


}
