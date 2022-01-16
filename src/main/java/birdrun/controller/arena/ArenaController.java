package birdrun.controller.arena;

import birdrun.controller.MatrixFactory;
import birdrun.controller.MusicController;
import birdrun.model.*;
import birdrun.state.Command;
import birdrun.viewer.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public static int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static boolean isCollectable(Element e) {
        return (e.getClass().getSuperclass() == Collectable.class);
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
        musicController.starBackGroundMusic();
    }

    public void pauseBgMusic() {
        musicController.stopBackGroundMusic();
    }

    public void resumeBgMusic() {
        musicController.resumeBackGroundMusic();
    }

    public void resetBgMusic() {
        musicController.resetBackGroundMusic();
    }

    public void addRandomElem(FallingElem elem, int numberOfElem) {
        int x, y = 2;

        for (int i = 0; i < numberOfElem; i++) {

            x = chooseMatrixCol(7);

            if (arenaModel.matrixGetPos(new Position(x, y)).getChar().equals(' ')) {
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
    }

    public int chooseMatrixCol(int bias) {

        List<Integer> list = new ArrayList<Integer>();

        int smallerBlockTowerIndex = 1 + arenaModel.getMatrix().getSmallerCol(blockChar);

        list.add(smallerBlockTowerIndex);

        for (int j = 0; j < bias; j++) {
            list.add(randInt(1, width - 2));
        }

        return list.get(randInt(0, list.size() - 1));

    }

    public void updateArena() {

        if (!arenaUpdater.updateArena()) {
            musicController.stopBackGroundMusic();
            musicController.playDeadSound();
        }
    }

    public boolean canBirdMove(Position pos) {

        boolean InBorder = inBorderBird(pos);
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

    public boolean inBorderBird(Position pos) {
        return !(pos.getX() < width - 1 && pos.getX() > 0 && pos.getY() < height - 1 && pos.getY() > 3);
    }

    public boolean moveBird(Position pos) {

        if (canBirdMove(pos)) {
            arenaModel.setBirdPos(pos);
            return true;
        }
        return false;

    }

    public void birdFly(Bird bird) {

        int stamina = bird.getStamina();
        if (stamina > 20) {
            moveBird(bird.moveUp(1));
            stamina -= 1; //11
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
        } else if (elem.equals(blockChar)) return canApplyGravityBlock(belowPos, new Position(x, y));

        else if (isCollectable(e)) return canApplyGravityCollectable(e, belowElem);

        return belowElem == ' ';
    }

    private boolean canApplyGravityBlock(Element belowPos, Position pos) {
        if (isCollectable(belowPos)) {
            arenaModel.matrixSetPos(new Block(pos.getX(), pos.getY() + 1, blockChar, blockColor));
            return true;
        } else if (belowPos.getChar().equals(blockChar)) return false;
        else if (belowPos.getChar().equals(birdChar)) {
            arenaModel.matrixSetPos(new EmptyElement(pos.getX(), pos.getY(), ' ', "#000000"));
            musicController.playDamageSound();
            arenaModel.birdTakeDamage(1);
            return true;
        }
        return belowPos.getChar().equals(' ');
    }

    private boolean canApplyGravityCollectable(Element elem, Character belowElem) {
        if (belowElem.equals(blockChar)) return false;

        if (belowElem.equals(birdChar)) {

            Bird bird = arenaModel.getBird();

            if (arenaModel.matrixGetPos(new Position(bird.getPositionX(), bird.getPositionY() + 1)).getChar() != ' ') {
                musicController.playCoinSound();

                if (elem.getChar().equals(coinChar)) arenaModel.birdPickCoins(1);
                else if (elem.getChar().equals(lifeChar)) arenaModel.addPlayerHp(1);

                else{
                    System.out.println("HERE");
                }

            }

            return true;

        }
        return belowElem.equals(' ');
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