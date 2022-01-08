import com.googlecode.lanterna.graphics.TextGraphics;

public class EmptyElement extends Element {
    public EmptyElement(int x, int y, Character defaultChar, String s) {
        super(x,y,defaultChar,s);
    }

    @Override
    public void gravityMove() {

    }

    @Override
    public void draw(TextGraphics graphics) {

    }
}
