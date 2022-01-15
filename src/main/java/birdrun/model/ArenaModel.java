package birdrun.model;

public class ArenaModel {

    private final int width;
    private final int height;
    private Bird bird;
    private Matrix matrix;

    public ArenaModel(Dimensions dimensions, Matrix matrix, String birdColor) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();
        this.bird = new Bird(new Position(width / 2, height / 2), '&', birdColor);
        this.matrix = matrix;
    }

    public Dimensions getDimensions() {
        return new Dimensions(width, height);
    }


    public int getPlayerScore() {
        return bird.getCoinCount();
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public int getPlayerHp() {
        return bird.getHp();
    }

    public Bird getBird() {
        return this.bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public void setBirdColor(String newColor) {
        this.bird.updateColor(newColor);
    }

    public void removeMatrixBottomRow() {

        for (int y = height - 2; y > 1; y--)
            for (int x = width - 1; x > 1; x--)
                matrix.getPos(x, y).gravityMove();

    }

    public void matrixSetPos(Element element) {
        matrix.setPos(element);
    }

    public void setBirdPos(Position pos) {
        this.bird.setPos(pos);
    }

    public void birdPickCoins(int n) {
        this.bird.pickCoin(n);
    }

    public Element matrixGetPos(Position pos) {
        return matrix.getPos(pos);
    }

    public void addPlayerHp(int hp) {
        this.bird.addHp(hp);
    }

    public void birdTakeDamage(int n) {
        this.bird.takeDamage(n );
    }
}