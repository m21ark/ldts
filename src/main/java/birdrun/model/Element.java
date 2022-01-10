package birdrun.model;


import com.googlecode.lanterna.graphics.TextGraphics;

@SuppressWarnings({"EqualsHashCode", "EqualsGetClass"})

public abstract class Element {

    protected String color;
    protected Character character;
    protected Position position = new Position(0, 0);

    public Element(int x, int y, Character character, String color) {
        this.position.setX(x);
        this.position.setY(y);
        this.character = character;
        this.color = color;
    }

    public Element(Position pos, Character character, String color) {
        this.position.set(pos);
        this.character = character;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Character getChar() {
        return character;
    }

    public void setPos(Position newPos) {
        position = newPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Element p = (Element) o;
        return   p.getChar().equals(character) && p.getPosition().equals(position);
    }

    public abstract void gravityMove();


    public abstract void draw(TextGraphics graphics);

}
