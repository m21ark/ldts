package birdrun.model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Extra_Life extends Collectable {

    public Extra_Life(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Extra_Life(Position pos, Character character, String color) {
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