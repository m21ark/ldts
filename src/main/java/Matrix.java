import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private final List<List<Element>> matrix = new ArrayList<>();
    private  int width;
    private  int height;

    Matrix(int width, int height, Character defaultChar) {

    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public boolean setPos(Element c) {
        return false;
    }

    public Element getPos(int x, int y) {
        return null;
    }

    public Element getPos(Position pos) {
        return null;
    }

    public List<List<Element>> getMatrix() {
        return matrix;
    }


    public void print() {

    }


}