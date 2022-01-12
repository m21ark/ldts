package birdrun.viewer.menu;

import birdrun.model.Dimensions;

public class MenuViewer {

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

}
