public class Block extends Element {

    public Block(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }
    public Block(Position pos, Character character, String color) {
        super(pos, character, color);
    }

    public void move(int x, int y) {
        position.setX(position.getX() + x);
        position.setY(position.getY() + y);
    }
}
