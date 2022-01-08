import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Collectable {

    public Coin(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Coin(Position pos, Character character, String color) {
        super(pos, character, color);
    }

    @Override
    public void gravityMove() {

    }

    @Override
    public void draw(TextGraphics graphics) {

    }

}