package birdrun.controller;

import birdrun.model.Dimensions;
import birdrun.state.Command;
import birdrun.state.KeyboardObserver;
import birdrun.state.states.MenuState;
import birdrun.viewer.GameViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController {

    private final Screen screen;
    private final TextGraphics graphics;
    private final ArenaController arena;
    private final MenuState menuState;
    private final GameViewer gameViewer;
    private final KeyboardObserver keyboardObserver;
    private KeyStroke key;
    private STATE state = STATE.START;


    public GameController(Dimensions dimensions) throws IOException, URISyntaxException, FontFormatException {

        this.screen = new ScreenFactory().getScreen(dimensions, 26);
        this.graphics = screen.newTextGraphics();
        this.arena = new ArenaController(dimensions);
        this.menuState = new MenuState(dimensions, graphics, "#3A656C", "#FFFFFF");
        this.gameViewer = new GameViewer();
        this.keyboardObserver = new KeyboardObserver(this.screen);


    }

    public void startMenuState() {


        try {
            screen.clear();
            menuState.drawState(MenuState.MENU_STATE.INITIAL);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {

            Command.COMMAND command = keyboardObserver.listenRead();

            if (command == null) continue;

            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                this.state = STATE.GAME;
                break;
            } else if (command == Command.COMMAND.UP) {
                this.state = STATE.INTRUCTIONS;
                break;
            }


        }


    }

    public void instructionsMenuState() {

        try {
            screen.clear();
            menuState.drawState(MenuState.MENU_STATE.INSTRUCTIONS);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {
            Command.COMMAND command = keyboardObserver.listenRead();

            if (command == null) continue;

            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                this.state = STATE.START;
                break;
            }

        }


    }

    public void deathMenuState() {


        try {
            screen.clear();
            menuState.drawState(MenuState.MENU_STATE.DEATH, arena.getArenaModel().getPlayerScore());
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {

            Command.COMMAND command = keyboardObserver.listenRead();

            if (command == null) continue;

            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                this.state = STATE.START;
                arena.reloadArena();
                break;
            }

        }

    }

    public void pauseMenuState() {

        try {
            screen.clear();
            menuState.drawState(MenuState.MENU_STATE.PAUSE);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {


            Command.COMMAND command = keyboardObserver.listenRead();

            if (command == null) continue;

            if (command == Command.COMMAND.QUIT) System.exit(0);
            else if (command == Command.COMMAND.SELECT) {
                this.state = STATE.START;
                break;
            }

        }

    }

    public void gameState() {

        int gameLoopInt = 0;
        int resetCountGameLoop = 1;
        boolean runGame = true;

        arena.startBgMusic();

        while (arena.playerAlive()) {

            try {

                screen.clear();
                gameViewer.draw(screen, graphics, arena.getArenaModel(), arena.getArenaViewer());
                screen.refresh();

                key = screen.pollInput();

                runGame = arena.processKey(key, screen);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!runGame) {
                //Pause game
                arena.pauseBgMusic();

                this.state = STATE.PAUSE;
                break;

            }

            if (gameLoopInt % 25 == 0) {
                arena.addRandomBlock(1);
                arena.applyGravity();
            }
            if (gameLoopInt == 250) {
                arena.addRandomCoin(1);
                gameLoopInt = 0;
                resetCountGameLoop++;
            }

            if (resetCountGameLoop % 25 == 0) {
                arena.addRandomLife();
                gameLoopInt = 0;
                resetCountGameLoop++;
            }


            gameLoopInt++;

            arena.update();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!arena.playerAlive())
                this.state = STATE.DEATH;
        }



    }

    public void run() throws IOException {


        while (true) {

            switch (this.state) {
                case START:
                    startMenuState();
                    break;
                case DEATH:
                    deathMenuState();
                    break;
                case PAUSE:
                    pauseMenuState();
                    break;
                case GAME:
                    gameState();
                    break;
                case INTRUCTIONS:
                    instructionsMenuState();
                    break;
                case NONE:
                    System.exit(0);

            }
        }


    }

    public enum STATE {START, INTRUCTIONS, GAME, DEATH, PAUSE, NONE}


}



