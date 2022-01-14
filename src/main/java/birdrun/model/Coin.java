package birdrun.model;

public class Coin extends Collectable {

    public Coin(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Coin(Position pos, Character character, String color) {
        super(pos, character, color);
    }


}