package birdrun.model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bird extends Element {

    private int hp = 3;
    private int coinCount = 0;
    private int stamina = 200;

    public Bird(int x, int y, Character character, String color) {
        super(x, y, character, color);
    }

    public Bird(Position pos, Character character, String color) {
        super(pos, character, color);
    }

    @Override
    public void gravityMove() {
        moveDown(1);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.position.getX(), this.position.getY()), this.character.toString());
    }

    public int takeDamage() {
        return --hp;
    }

    public int takeDamage(int n) {
        hp -= n;
        return hp;
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

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int newStamina) {
        if (newStamina > 200) this.stamina = 200;
        else this.stamina = Math.max(newStamina, 0);
    }

    public void updateColor(String newColor) {
        this.color = newColor;
    }

}