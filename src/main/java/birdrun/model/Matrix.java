package birdrun.model;

import java.util.ArrayList;
import java.util.List;


public class Matrix {

    private final int width;
    private final int height;
    private final List<List<Element>> matrix = new ArrayList<>();

    public Matrix(Dimensions dimensions, Character defaultChar) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();

        for (int i = 0; i < height; i++) {
            List<Element> aux = new ArrayList<>();
            for (int j = 0; j < width; j++)
                aux.add(new EmptyElement(i, j, defaultChar, "#000000"));
            matrix.add(aux);
        }
    }

    public static int indexOfSmallest(List<Integer> array) {

        if (array.size() == 0) return -1;

        int index = 0;
        int min = array.get(index);

        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) < min) {
                min = array.get(i);
                index = i;
            }
        }
        return index;

    }

    static <T> List<List<T>> transpose(List<List<T>> table) {
        List<List<T>> ret = new ArrayList<List<T>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }

    public boolean setPos(Element c) {
        if (c == null) return false;
        int x = c.getPosition().getX();
        int y = c.getPosition().getY();

        if (x < 0 || y < 0 || x >= width || y >= height) return false;

        matrix.get(y).set(x, c);
        return true;
    }

    public Element getPos(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return null;
        return matrix.get(y).get(x);
    }

    public Element getPos(Position pos) {
        return getPos(pos.getX(), pos.getY());
    }

    public int countLine(Character ch, List<Element> list) {
        int count = 0;

        for (Element elem : list)
            if (elem.getChar().equals(ch)) count++;

        return count;
    }

    public int getSmallerCol(Character ch) {

        List<Integer> arrCount = new ArrayList<Integer>();

        List<List<Element>> transposed = transpose(matrix);

        for (int i = 1; i < width - 1; i++)
            arrCount.add(countLine(ch, transposed.get(i)));

        return indexOfSmallest(arrCount);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<List<Element>> getMatrix() {
        return matrix;
    }
}