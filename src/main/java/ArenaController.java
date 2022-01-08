import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Random;


public class ArenaController {

    //Characters
    public final static Character birdChar = 'B';
    public final static Character blockChar = 'X';
    public final static Character borderChar = '#';
    public final static Character coinChar = 'C';

    //Colors
    private final static String textColor = "#000000";
    private final static String birdColor = "#FFFFFF";
    private final static String bgColor = "#3A656C";
    private final static String coinColor = "#FFAA11";
    private final static String blockColor = "#4B351C";
    private final static String borderColor = "#653A6C";

    //Attributes
    private final int width;
    private final int height;
    private final Bird bird;
    private Matrix matrix;
    private final ArenaViewer arenaViewer;
    private final ArenaModel arenaModel;

    ArenaController(int width, int height) {
        this.width = width;
        this.height = height;
        this.bird = new Bird(new Position(width / 2, height / 2), 'B', birdColor);
        matrix = new MatrixFactory().getMatrix(width, height, borderChar, borderColor);
        matrix.setPos(this.bird);
        this.arenaViewer = new ArenaViewer(width,height,bgColor,textColor);
        this.arenaModel = new ArenaModel(width,height, matrix, birdColor);
    }



    public ArenaViewer getArenaViewer(){
        return this.arenaViewer;
    }

    public int getPlayerScore(){
        return bird.getCoinCount();
    }

    private int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - (min)) + 1) + (min);
    }

    public Matrix getMatrix() {
        return matrix;
    }


    public void addRandomCoin(int numberOfCoin) {
        int x, y;

        for (int i = 0; i < numberOfCoin; i++) {
            x = randInt(1, width - 2);
            y = 2;
            matrix.setPos(new Coin(x, y, coinChar, coinColor));
            matrix.setPos(new Coin(x, y, coinChar, coinColor));
        }
    }

    public void addRandomBlock(int numberOfBlock) {
        int x, y;

        for (int i = 0; i < numberOfBlock; i++) {
            x = randInt(1, width - 2);
            y = 2;
            matrix.setPos(new Block(x, y, blockChar, blockColor));
            matrix.setPos(new Block(x, y, blockChar, blockColor));
        }
    }

    public boolean canBirdMove(Position pos) {
        boolean notInBorder = pos.getX() < width - 1 && pos.getX() > 0 && pos.getY() < height - 1 && pos.getY() > 5;
        Character NewPos = matrix.getPos(pos).getChar();
        boolean isNewPosFree = NewPos!= blockChar;
        if(NewPos == coinChar) {
            if(pos.getX()!=bird.getPositionX())
                bird.pickCoin(1);
            else if (matrix.getPos(new Position(pos.getX(), pos.getY() + 1)).getChar() != ' ')
                bird.pickCoin(1);
        }
        return notInBorder && isNewPosFree;
    }

    public boolean moveBird(Position pos) {
        if (canBirdMove(pos)) {
            bird.setPos(pos);
            return true;
        } else return false;
    }

    public void applyGravity() {

        for (int y = height - 1; y > 1; y--)
            for (int x = width - 1; x >= 1; x--) {
                Element e = matrix.getPos(x, y);
                Character ch = e.getChar();
                if (ch != borderChar && ch != birdChar)
                    if (canApplyGravity(e))
                        e.gravityMove();
            }

        moveBird(bird.moveDown(1));

    }

    private boolean canApplyGravity(Element e) {
        int x = e.getPositionX();
        int y = e.getPositionY();


        Element tempPos = matrix.getPos(x, y + 1);

        if(tempPos==null) return false;

        Character belowElem =tempPos.getChar();

        boolean canApply = belowElem == ' ';

        //Situations
        if (e.getChar() == blockChar && belowElem == coinChar) {
            canApply = true;
            matrix.setPos(new Block(x, y + 1, blockChar, blockColor));
        } else if (e.getChar() == blockChar && belowElem == birdChar) {
            canApply = true;
            bird.takeDamage();
        } else if (e.getChar() == coinChar && belowElem == birdChar) {
            canApply = true;
            if(matrix.getPos(new Position(bird.getPositionX(),bird.getPositionY()+1)).getChar() != ' ')
                bird.pickCoin(1);

        } else if (e.getChar() == birdChar && belowElem == coinChar) {
            canApply = true;
            e.setPos(new Position(x, y));
            matrix.setPos(e);
        }


        return canApply;
    }

    private void matrixUpdate() {
        Matrix newMatrix =  new MatrixFactory().getMatrix(width, height, borderChar, borderColor);
        newMatrix.setPos(this.bird);

        Element b = null;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Element e = this.matrix.getPos(x, y);
                if (e.getChar() == birdChar) b = e;
                else if (e.getChar() != ' ') newMatrix.setPos(e);
            }

        newMatrix.setPos(b);
        this.matrix = newMatrix;
    }

    public boolean playerAlive() {
        return bird.isAlive();
    }

    public void update() {
        matrixUpdate();
        if (isMatrixBottomRowFull()) removeMatrixBottomRow();
    }

    private void removeMatrixBottomRow() {
        for (int y = height - 2; y > 1; y--)
            for (int x = width - 1; x > 1; x--)
                matrix.getPos(x, y).gravityMove();
    }

    private boolean isMatrixBottomRowFull() {
        boolean isLineFull = true;

        for (int x = 0; x < width; x++) {
            Character c = matrix.getPos(x, height - 2).getChar();
            if (c == ' ' || c == birdChar) isLineFull = false;
        }
        return isLineFull;
    }


    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        if (key == null) return true;
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveBird(bird.moveLeft(1));
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveBird(bird.moveRight(1));
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            moveBird(bird.moveUp(1));
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            moveBird(bird.moveDown(1));
        } else if (key.getKeyType() == KeyType.EOF) {
            return false;
        }


        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
            System.exit(0);
        }

        return true;

    }

    public int getPlayerHp() {
        return bird.getHp();
    }
}