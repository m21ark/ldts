public class Bird extends Element {

    private  int hp = 3;
    private int coinCount = 0;

    public Bird(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }
    public Bird(Position pos, Character character, String color) {
        super(pos, character, color);
    }

    public int takeDamage() {
        return 0;
    }

    public int getHp() {
        return 0;
    }

    public void setHp(int newHp) {

    }

    public void addHp(int newHp) {

    }

    public boolean isAlive() {
        return false;
    }

    public int pickCoin(int numCoins) {
        return 0;
    }

    public int getCoinCount() {
        return 0;
    }

    public void setCoinCount(int numCoins) {

    }

    public Position moveUp(int delta) {
        return null;
    }

    public Position moveDown(int delta) {
        return null;
    }

    public Position moveLeft(int delta) {
        return null;
    }

    public Position moveRight(int delta) {
        return null;
    }

}
