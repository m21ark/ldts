package birdrun.controller;

import birdrun.model.Block;
import birdrun.model.Dimensions;
import birdrun.model.Matrix;

public class MatrixFactory {

    public Matrix getMatrix(Dimensions dimensions, Character borderChar, String borderColor) {

        int width = dimensions.getWidth();
        int height = dimensions.getHeight();

        Matrix temp = new Matrix(dimensions, ' ');

        boolean setPos;

        for (int c = 0; c < width; c++) {
            for (int i : new int[]{0, height - 1}) {
                setPos = temp.setPos(new Block(c, i, borderChar, borderColor));
                if (!setPos) return null;
            }
        }

        for (int r = 1; r < height - 1; r++) {
            for (int i : new int[]{0, width - 1}) {
                setPos = temp.setPos(new Block(i, r, borderChar, borderColor));
                if (!setPos) return null;
            }
        }

        return temp;

    }

}
