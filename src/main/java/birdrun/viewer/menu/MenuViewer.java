package birdrun.viewer.menu;

import birdrun.model.Dimensions;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class MenuViewer {

    protected final int width;
    protected final int height;
    protected final String bgColor;
    protected final String textColor;

    public MenuViewer(Dimensions dimensions, String bgColor, String textColor) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    protected void writeSMS(TextGraphics graphics, String sms, int y) {

        graphics.putString(new TerminalPosition(width / 2 - sms.length() / 2, height / 2 - y), sms);

    }

}
