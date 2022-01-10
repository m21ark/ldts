package birdrun.viewer;

import birdrun.model.Dimensions;
import birdrun.model.Element;
import birdrun.model.Matrix;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ArenaViewer {

    private final int width;
    private final int height;
    private final String bgColor;
    private final String textColor;

    public ArenaViewer(Dimensions dimensions, String bgColor, String textColor) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    public void drawMatrix(TextGraphics graphics, Matrix matrix) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                Element e = matrix.getPos(x, y);
                if (e != null) if (e.getChar() != ' ') e.draw(graphics);
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
