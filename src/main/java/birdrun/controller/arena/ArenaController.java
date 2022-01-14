package birdrun.controller.arena;

import birdrun.controller.MatrixFactory;
import birdrun.controller.MusicController;
import birdrun.model.*;
import birdrun.state.Command;
import birdrun.viewer.ArenaViewer;

import java.io.IOException;
import java.util.Random;


public class ArenaController {

    //Characters
    public final static Character birdChar = '&';
    public final static Character blockChar = 'X';
    public final static Character borderChar = '#';
    public final static Character coinChar = '^';
    public final static Character lifeChar = '*';

    //Colors
    private final static String textColor = "#000000";
    private final static String bgColor = "#3A656C";
    private final static String coinColor = "#FFAA11";
    private final static String blockColor = "#4B351C";
    private final static String borderColor = "#653A6C";
    private final static String lifeColor = "#16C527";
    private static String birdColor = "#FFFFFF";

    //Attributes
    private final int width;
    private final int height;
    private MusicController musicController = null;
    private ArenaViewer arenaViewer;
    private ArenaModel arenaModel;


    public ArenaController(Dimensions dimensions) {

        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();


        Bird bird = new Bird(new Position(width / 2, height / 2), birdChar, birdColor);
        Matrix matrix = new MatrixFactory().getMatrix(new Dimensions(width, height), borderChar, borderColor);
        matrix.setPos(bird);
        this.arenaViewer = new ArenaViewer(new Dimensions(width, height), bgColor, textColor);
        this.arenaModel = new ArenaModel(new Dimensions(width, height), matrix, birdColor);

        this.musicController = new MusicController();

    }


    public void startBgMusic() {
        musicController.starBackGroundMusic();
    }


    public ArenaViewer getArenaViewer() {
        return this.arenaViewer;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }

    public void reloadArena() {
        birdColor = "#FFFFFF";
        Bird bird = new Bird(new Position(width / 2, height / 2), birdChar, birdColor);
        Matrix matrix = new MatrixFactory().getMatrix(new Dimensions(width, height), borderChar, borderColor);
        matrix.setPos(bird);
        this.arenaViewer = new ArenaViewer(new Dimensions(width, height), bgColor, textColor);
        this.arenaModel = new ArenaModel(new Dimensions(width, height), matrix, birdColor);
    }

    private int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void addRandomCoin(int numberOfCoin) {
        int x, y;
        Matrix matrix = arenaModel.getMatrix();

        for (int i = 0; i < numberOfCoin; i++) {
            x = randInt(1, width - 2);
            y = 2;
            matrix.setPos(new Coin(x, y, coinChar, coinColor));
            matrix.setPos(new Coin(x, y, coinChar, coinColor));
        }

        arenaModel.setMatrix(matrix);
    }

    public void addRandomLife() {
        int x, y;
        Matrix matrix = arenaModel.getMatrix();

        x = randInt(1, width - 2);
        y = 2;
        matrix.setPos(new Coin(x, y, lifeChar, lifeColor));


        arenaModel.setMatrix(matrix);
    }

    public void addRandomBlock(int numberOfBlock) {
        int x, y;
        Matrix matrix = arenaModel.getMatrix();

        for (int i = 0; i < numberOfBlock; i++) {
            x = randInt(1, width - 2);
            y = 2;
            matrix.setPos(new Block(x, y, blockChar, blockColor));
            matrix.setPos(new Block(x, y, blockChar, blockColor));
        }
        arenaModel.setMatrix(matrix);
    }

    public boolean canBirdMove(Position pos) {

        Matrix matrix = arenaModel.getMatrix();
        Bird bird = arenaModel.getBird();

        boolean notInBorder = pos.getX() < width - 1 && pos.getX() > 0 && pos.getY() < height - 1 && pos.getY() > 5;

        if (matrix.getPos(pos) == null) return false;

        Character NewPos = matrix.getPos(pos).getChar();
        boolean isNewPosFree = !NewPos.equals(blockChar);

        if (NewPos.equals(coinChar)) {
            if (pos.getX() != bird.getPositionX()) {
                musicController.playCoinSound();
                bird.pickCoin(1);
            } else if (matrix.getPos(new Position(pos.getX(), pos.getY() + 1)).getChar() != ' ') {
                musicController.playCoinSound();
                bird.pickCoin(1);
            }
        }

        if (NewPos.equals(lifeChar)) {
            if (pos.getX() != bird.getPositionX()) {
                musicController.playCoinSound();
                bird.addHp(1);
            } else if (matrix.getPos(new Position(pos.getX(), pos.getY() + 1)).getChar() != ' ') {
                musicController.playCoinSound();
                bird.addHp(1);
            }
        }

        arenaModel.setBird(bird);

        return notInBorder && isNewPosFree;
    }

    public boolean moveBird(Position pos) {
        Bird bird = arenaModel.getBird();

        if (canBirdMove(pos)) {
            bird.setPos(pos);
            arenaModel.setBird(bird);
            return true;
        } else return false;
    }

    public void applyGravity() {

        Matrix matrix = arenaModel.getMatrix();
        Bird bird = arenaModel.getBird();

        for (int y = height - 1; y > 1; y--)
            for (int x = width - 1; x >= 1; x--) {
                Element e = matrix.getPos(x, y);
                Character ch = e.getChar();
                if (!ch.equals(borderChar) && !ch.equals(birdChar)) if (canApplyGravity(e)) e.gravityMove();
            }

        moveBird(bird.moveDown(1));
        bird.setStamina(bird.getStamina() + 6);
        arenaModel.setBird(bird);

    }

    private boolean canApplyGravity(Element e) {
        int x = e.getPositionX();
        int y = e.getPositionY();

        Matrix matrix = arenaModel.getMatrix();
        Bird bird = arenaModel.getBird();


        Element tempPos = matrix.getPos(x, y + 1);

        if (tempPos == null) return false;

        Character belowElem = tempPos.getChar();

        boolean canApply = belowElem == ' ';

        //Situations
        if (e.getChar().equals(blockChar) && belowElem.equals(coinChar)) {
            canApply = true;
            matrix.setPos(new Block(x, y + 1, blockChar, blockColor));

        } else if (e.getChar().equals(blockChar) && belowElem.equals(lifeChar)) {
            canApply = true;
            matrix.setPos(new Block(x, y + 1, blockChar, blockColor));
        } else if (e.getChar().equals(blockChar) && belowElem.equals(birdChar)) {
            canApply = true;
            musicController.playDamageSound();
            bird.takeDamage();

        } else if (e.getChar().equals(coinChar) && belowElem.equals(birdChar)) {
            canApply = true;
            if (matrix.getPos(new Position(bird.getPositionX(), bird.getPositionY() + 1)).getChar() != ' ') {
                musicController.playCoinSound();
                bird.pickCoin(1);
            }

        } else if (e.getChar().equals(birdChar) && belowElem.equals(coinChar)) {
            canApply = true;
            e.setPos(new Position(x, y));
            matrix.setPos(e);

        } else if (e.getChar().equals(birdChar) && belowElem.equals(lifeChar)) {
            canApply = true;
            e.setPos(new Position(x, y));
            matrix.setPos(e);
        }


        arenaModel.setBird(bird);
        arenaModel.setMatrix(matrix);

        return canApply;
    }

    private void matrixUpdate() {

        Matrix matrix = arenaModel.getMatrix();
        Bird bird = arenaModel.getBird();

        Matrix newMatrix = new MatrixFactory().getMatrix(new Dimensions(width, height), borderChar, borderColor);
        newMatrix.setPos(bird);

        Element b = null;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Element e = matrix.getPos(x, y);
                if (e.getChar().equals(birdChar)) b = e;
                else if (e.getChar() != ' ') newMatrix.setPos(e);
            }

        newMatrix.setPos(b);

        arenaModel.setBird(bird);
        arenaModel.setMatrix(newMatrix);

    }

    public boolean playerAlive() {
        return arenaModel.getPlayerHp() > 0;
    }

    public void update() {

        Bird bird = arenaModel.getBird();

        if (bird.getStamina() < 50) {
            birdColor = "#C51663";
        } else if (bird.getStamina() < 100) {
            birdColor = "#BEC516";
        } else {
            birdColor = "#FFFFFF";
        }
        bird.updateColor(birdColor);

        matrixUpdate();
        if (isMatrixBottomRowFull()) removeMatrixBottomRow();

        if (!playerAlive()) {
            musicController.stopBackGroundMusic();
            musicController.playDeadSound();
        }

        arenaModel.setBird(bird);
    }

    private void removeMatrixBottomRow() {
        Matrix matrix = arenaModel.getMatrix();

        for (int y = height - 2; y > 1; y--)
            for (int x = width - 1; x > 1; x--)
                matrix.getPos(x, y).gravityMove();

        arenaModel.setMatrix(matrix);
    }

    private boolean isMatrixBottomRowFull() {
        boolean isLineFull = true;
        Matrix matrix = arenaModel.getMatrix();

        for (int x = 0; x < width; x++) {
            Character c = matrix.getPos(x, height - 2).getChar();
            if (c == ' ' || c.equals(birdChar)) isLineFull = false;
        }
        return isLineFull;
    }


    public void pauseBgMusic() {
        musicController.stopBackGroundMusic();
    }

    public void resumeBgMusic() {

        musicController.resumeBackGroundMusic();
    }


    public boolean executeCommand(Command.COMMAND command) throws IOException {
        Bird bird = arenaModel.getBird();

        if (command == null) return true;


        switch (command) {
            case UP:
                moveBird(bird.moveUp(1));
                break;
            case DOWN:
                moveBird(bird.moveDown(1));
                break;
            case LEFT:
                moveBird(bird.moveLeft(1));
                break;
            case RIGHT:
                moveBird(bird.moveRight(1));
                break;
            case PAUSE:
                return false;
            case QUIT:
                System.exit(0);
            case NONE:
                return true;
        }

        arenaModel.setBird(bird);

        return true;

    }

    private void birdFly(Bird bird) {

        int stamina = bird.getStamina();

        if (stamina > 30) {
            moveBird(bird.moveUp(1));
            arenaModel.setBird(bird);
            stamina -= 12;
            bird.setStamina(stamina);
            arenaModel.setBird(bird);
        }

    }


    public void resetBgMusic() {
        musicController.resetBackGroundMusic();
    }

    public int getPlayerScore() {
        return arenaModel.getPlayerScore();
    }
}
