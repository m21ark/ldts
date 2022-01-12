package birdrun.controller;

import birdrun.model.Dimensions;
import birdrun.state.states.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController {

    private final Screen screen;
    private final TextGraphics graphics;
    private final ArenaController arena;
    private final MenuState menuState;
    private final GameState gameState;
    private final PauseMenuState pauseMenuState;
    private final StartMenuState startMenuState;
    private final DeathMenuState deathMenuState;
    private final InstructionsMenuState instructionsMenuState;
    private STATE state = STATE.START;


    public GameController(Dimensions dimensions) throws IOException, URISyntaxException, FontFormatException {

        this.screen = new ScreenFactory().getScreen(dimensions, 26);
        this.graphics = screen.newTextGraphics();
        this.arena = new ArenaController(dimensions);
        this.menuState = new MenuState(dimensions, graphics, "#3A656C", "#FFFFFF");
        this.gameState = new GameState(arena, screen, graphics);
        this.pauseMenuState = new PauseMenuState(screen, menuState);
        this.startMenuState = new StartMenuState(screen, menuState);
        this.deathMenuState = new DeathMenuState(screen, menuState);
        this.instructionsMenuState = new InstructionsMenuState(screen, menuState);
    }

    public void run() throws IOException {


        while (true) {
            switch (this.state) {
                case START -> this.state = startMenuState.start();
                case DEATH -> this.state = deathMenuState.start(arena.getArenaModel().getPlayerScore());
                case PAUSE -> this.state = pauseMenuState.start();
                case GAME -> this.state = gameState.start();
                case INSTRUCTIONS -> this.state = instructionsMenuState.start();
                case NONE -> System.exit(0);
            }
        }
    }


    public enum STATE {START, INSTRUCTIONS, GAME, DEATH, PAUSE, NONE}


}



