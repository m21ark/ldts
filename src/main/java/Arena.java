import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class Arena {

    //Characters
    public final static Character birdChar = 'B';
    public final static Character blockChar = 'X';
    public final static Character borderChar = '#';
    public final static Character coinChar = 'C';

    //Colors
    private final static String textColor = "#000000";
    private final static String birdColor = "#FFFFFF";
    private final static String bgColor = "#3A656C";
    private final static String coinColor = "#FFAA11";
    private final static String blockColor = "#4B351C";
    private final static String borderColor = "#653A6C";

    //Attributes
    private int width;
    private int height;
    private Bird bird;
    private Matrix matrix;

    Arena(int width, int height) {

    }

    Arena(int width, int height, Bird bird) {

    }

    private int randInt(int min, int max) {
        return 0;
    }

    public Matrix getMatrix() {
        return null;
    }

    public Matrix createMatrix(int width, int height, Character defaultChar) {
        return null;
    }

    public void addRandomElem(int numberOfElem, Character Char) {

    }

    public boolean canBirdMove(Position pos) {
        return false;
    }

    public boolean moveBird(Position pos) {
        return false;
    }

    private boolean detectCollision(Matrix newM, Element b) {
        return false;
    }

    public void applyGravity() {

    }

    private boolean canApplyGravity(Element e) {
        return false;
    }


    private void matrixDraw(TextGraphics graphics) {

    }


    private void matrixUpdate() {

    }

    public boolean playerAlive() {
        return false;
    }

    public void update() {

    }

    private void removeMatrixBottomRow() {

    }

    private boolean isMatrixBottomRowFull() {
        return false;
    }

    public void draw(TextGraphics graphics) {

    }


    public void drawLoadingScreen(TextGraphics graphics) {

    }

    public void drawDeathScreen(TextGraphics graphics) {

    }


    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        return false;
    }

}