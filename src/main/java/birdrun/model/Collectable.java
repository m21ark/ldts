package birdrun.model;

public abstract class Collectable extends Element {

    public Collectable(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Collectable(Position pos, Character character, String color) {
        super(pos, character, color);
    }


}
