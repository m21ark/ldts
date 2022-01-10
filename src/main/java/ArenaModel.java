public class ArenaModel {

    private final int width;
    private final int height;
    private Bird bird;
    private Matrix matrix;

    ArenaModel(Dimensions dimensions, Matrix matrix, String birdColor) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();
        this.bird = new Bird(new Position(width / 2, height / 2), 'B', birdColor);
        this.matrix = matrix;
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
}