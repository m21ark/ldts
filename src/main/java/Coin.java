import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
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
        position.setY(position.getY() + 1);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(this.position.getX(), this.position.getY()), this.character.toString());
    }

}