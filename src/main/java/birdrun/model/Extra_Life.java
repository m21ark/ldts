package birdrun.model;

public class Extra_Life extends Collectable {

    public Extra_Life(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Extra_Life(Position pos, Character character, String color) {
        super(pos, character, color);
    }

}