import com.googlecode.lanterna.graphics.TextGraphics;

public class Block extends Element {

    public Block(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Block(Position pos, Character character, String color) {
        super(pos, character, color);
    }

    @Override
    public void gravityMove() {

    }

    @Override
    public void draw(TextGraphics graphics) {

    }

    public void move(int x, int y) {
        position.setX(position.getX() + x);
        position.setY(position.getY() + y);
    }
}
