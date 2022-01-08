import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Random;


public class ArenaModel {

    private final int width;
    private final int height;
    private final Bird bird;
    private final Matrix matrix;

    ArenaModel(int width, int height, Matrix matrix, String birdColor) {
        this.width = width;
        this.height = height;
        this.bird = new Bird(new Position(width / 2, height / 2), 'B', birdColor);
        this.matrix = matrix;
    }


    public int getPlayerScore(){
        return bird.getCoinCount();
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public int getPlayerHp() {
        return bird.getHp();
    }



}