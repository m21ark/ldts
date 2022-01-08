import com.googlecode.lanterna.graphics.TextGraphics;

public class Bird extends Element {

    private int hp = 3;
    private int coinCount = 0;

    public Bird(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Bird(Position pos, Character character, String color) {
        super(pos, character, color);
    }

    @Override
    public void gravityMove() {

    }

    @Override
    public void draw(TextGraphics graphics) {

    }

    public int takeDamage() {
        return --hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int newHp) {
        hp = newHp;
    }

    public void addHp(int newHp) {
        hp += newHp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int pickCoin(int numCoins) {
        coinCount += numCoins;
        return coinCount;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int numCoins) {
        coinCount = numCoins;
    }

    public Position moveUp(int delta) {
        return new Position(position.getX(), position.getY() - delta);
    }

    public Position moveDown(int delta) {
        return new Position(position.getX(), position.getY() + delta);
    }

    public Position moveLeft(int delta) {
        return new Position(position.getX() - delta, position.getY());
    }

    public Position moveRight(int delta) {
        return new Position(position.getX() + delta, position.getY());
    }

}