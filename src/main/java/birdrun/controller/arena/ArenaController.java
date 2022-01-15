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
    public final static String textColor = "#000000";
    public final static String bgColor = "#3871A3";
    public final static String coinColor = "#FFAA11";
    public final static String blockColor = "#4B351C";
    public final static String borderColor = "#653A6C";
    public final static String lifeColor = "#16C527";
    public static String birdColor = "#FFFFFF";

    //Attributes
    private final int width;
    private final int height;

    private MusicController musicController = null;
    private ArenaViewer arenaViewer;
    private ArenaModel arenaModel;
    private ArenaUpdater arenaUpdater;

    public ArenaController(Dimensions dimensions) {

        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();

        Bird bird = new Bird(new Position(width / 2, height / 2), birdChar, birdColor);
        Matrix matrix = new MatrixFactory().getMatrix(dimensions, borderChar, borderColor);
        matrix.setPos(bird);
        this.arenaViewer = new ArenaViewer(dimensions, bgColor, textColor);
        this.arenaModel = new ArenaModel(dimensions, matrix, birdColor);
        this.arenaUpdater = new ArenaUpdater(arenaModel);
        this.musicController = new MusicController();

    }

    public void reloadArena() {
        birdColor = "#FFFFFF";
        Bird bird = new Bird(new Position(width / 2, height / 2), birdChar, birdColor);
        Matrix matrix = new MatrixFactory().getMatrix(new Dimensions(width, height), borderChar, borderColor);
        matrix.setPos(bird);
        this.arenaViewer = new ArenaViewer(new Dimensions(width, height), bgColor, textColor);
        this.arenaModel = new ArenaModel(new Dimensions(width, height), matrix, birdColor);
        this.arenaUpdater = new ArenaUpdater(arenaModel);
    }

    private int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public ArenaViewer getArenaViewer() {
        return this.arenaViewer;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }

    public int getPlayerScore() {
        return arenaModel.getPlayerScore();
    }

    public boolean playerAlive() {
        return arenaModel.getPlayerHp() > 0;
    }

    public void startBgMusic() {
        if (musicController != null) musicController.starBackGroundMusic();
    }

    public void pauseBgMusic() {
        if (musicController != null) musicController.stopBackGroundMusic();
    }

    public void resumeBgMusic() {
        if (musicController != null) musicController.resumeBackGroundMusic();
    }

    public void resetBgMusic() {
        if (musicController != null) musicController.resetBackGroundMusic();
    }

    public boolean isCollectable(Element e) {
        return (e.getClass().getSuperclass() == Collectable.class);
    }

    public void addRandomElem(FallingElem elem, int numberOfElem) {
        int x, y;

        for (int i = 0; i < numberOfElem; i++) {
            x = randInt(1, width - 2);
            y = 2;

            switch (elem) {
                case COIN:
                    arenaModel.matrixSetPos(new Coin(x, y, coinChar, coinColor));
                    break;
                case BLOCK:
                    arenaModel.matrixSetPos(new Block(x, y, blockChar, blockColor));
                    break;
                case LIFE:
                    arenaModel.matrixSetPos(new Coin(x, y, lifeChar, lifeColor));
                    break;
                default:
                    break;
            }
        }
    }

    public void updateArena() {

        if (!arenaUpdater.updateArena()) {
            musicController.stopBackGroundMusic();
            musicController.playDeadSound();
        }
    }

    public boolean canBirdMove(Position pos) {

        boolean InBorder = !(pos.getX() < width - 1 && pos.getX() > 0 && pos.getY() < height - 1 && pos.getY() > 5);
        if (InBorder) return false;
        Element newElem = arenaModel.matrixGetPos(pos);
        if (newElem == null) return false;
        Character NewPos = newElem.getChar();
        if (NewPos.equals(blockChar)) return false;

        boolean lateralMove = pos.getX() != arenaModel.getBird().getPositionX();
        boolean notMidAir = arenaModel.matrixGetPos(new Position(pos.getX(), pos.getY() + 1)).getChar() != ' ';

        if ((lateralMove || notMidAir) && isCollectable(newElem)) {
            musicController.playCoinSound();

            if (NewPos.equals(coinChar)) arenaModel.birdPickCoins(1);

            else if (NewPos.equals(lifeChar)) arenaModel.addPlayerHp(1);

        }

        return true;
    }

    public boolean moveBird(Position pos) {

        if (canBirdMove(pos)) {
            arenaModel.setBirdPos(pos);
            return true;
        }
        return false;

    }

    private void birdFly(Bird bird) {

        int stamina = bird.getStamina();
        if (stamina > 30) {
            moveBird(bird.moveUp(1));
            stamina -= 12;
            bird.setStamina(stamina);
            arenaModel.setBird(bird);
        }

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
        Character elem = e.getChar();

        Element belowPos = arenaModel.matrixGetPos(new Position(x, y + 1));
        if (belowPos == null) return false;
        Character belowElem = belowPos.getChar();

        if (elem.equals(birdChar)) {
            if (isCollectable(belowPos)) {
                e.setPos(new Position(x, y));
                arenaModel.matrixSetPos(e);
            }
            return true;

        } else if (elem.equals(blockChar)) {

            if (isCollectable(belowPos)) {
                arenaModel.matrixSetPos(new Block(x, y + 1, blockChar, blockColor));
                return true;
            } else if (belowElem.equals(blockChar)) return false;
            else if (belowElem.equals(birdChar)) {
                arenaModel.matrixSetPos(new EmptyElement(x, y, ' ', "#000000"));
                musicController.playDamageSound();
                arenaModel.birdTakeDamage(1);
                return true;
            }

        } else if (isCollectable(e)) {

            if (belowElem.equals(blockChar)) return false;

            if (belowElem.equals(birdChar)) {

                Bird bird = arenaModel.getBird();

                if (arenaModel.matrixGetPos(new Position(bird.getPositionX(), bird.getPositionY() + 1)).getChar() != ' ') {
                    musicController.playCoinSound();

                    if (elem.equals(coinChar)) arenaModel.birdPickCoins(1);
                    else if (elem.equals(lifeChar)) arenaModel.addPlayerHp(1);

                }

                return true;

            }

        }

        return belowElem == ' ';
    }

    public boolean executeCommand(Command.COMMAND command) throws IOException {
        Bird bird = arenaModel.getBird();

        if (command == null) return true;

        switch (command) {
            case UP:
                birdFly(bird);
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
            default:
                return true;
        }

        arenaModel.setBird(bird);
        return true;

    }

    public enum FallingElem {BLOCK, COIN, LIFE,}

}