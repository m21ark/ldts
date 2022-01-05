import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {

    protected String color;
    protected Character character;
    protected boolean fixedPos = false;
    protected Position position = new Position(0, 0);

    public Element(int x, int y, Character character, String color) {

    }

    public Element(Position pos, Character character, String color) {

    }

    public void gravityMoveDown() {

    }

    public Position getPosition() {return position;}
    public int getPositionX() {return position.getX();}
    public int getPositionY() {return position.getY();}
    public Character getChar(){return character;}
    public  boolean isFixedPos(){return fixedPos;}

    public void setChar(Character newChar) {this.character = newChar;}

    public void setPos(Position newPos){
        position  = newPos;
    }

    public Element setFixedPos(boolean newFixedPos) {
        return null;
    }


    public void draw(TextGraphics graphics) {

    }


}
