package birdrun.controller;

import birdrun.model.Block;
import birdrun.model.Dimensions;
import birdrun.model.Matrix;

public class MatrixFactory {

    public Matrix getMatrix(Dimensions dimensions, Character borderChar, String borderColor) {

        int width = dimensions.getWidth();
        int height = dimensions.getHeight();

        Matrix temp = new Matrix(dimensions, ' ');

        for (int c = 0; c < width; c++) {
            temp.setPos(new Block(c, 0, borderChar, borderColor));
            temp.setPos(new Block(c, height - 1, borderChar, borderColor));
        }

        for (int r = 1; r < height - 1; r++) {
            temp.setPos(new Block(0, r, borderChar, borderColor));
            temp.setPos(new Block(width - 1, r, borderChar, borderColor));
        }

        return temp;

    }

}
