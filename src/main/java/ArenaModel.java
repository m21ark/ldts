public class ArenaModel {

    private final int width;
    private final int height;
    private Bird bird;
    private Matrix matrix;

    ArenaModel(int width, int height, Matrix matrix, String birdColor) {
        this.width = width;
        this.height = height;
        this.bird = new Bird(new Position(width / 2, height / 2), 'B', birdColor);
        this.matrix = matrix;
    }


    public int getPlayerScore() {
        return bird.getCoinCount();
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public int getPlayerHp() {
        return bird.getHp();
    }


    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Bird getBird() {
        return this.bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }
}