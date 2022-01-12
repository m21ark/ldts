package birdrun.state.states;

import birdrun.controller.ArenaController;
import birdrun.controller.GameController;
import birdrun.viewer.GameViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameState {

    private int gameLoopInt = 0;
    private int resetCountGameLoop = 1;
    private boolean runGame = true;

    private final ArenaController arena;
    private final TextGraphics graphics;
    private final Screen screen;


    public GameState(ArenaController arena, Screen screen, TextGraphics graphics) {

        this.arena = arena;
        this.graphics = graphics;
        this.screen = screen;

    }

    public GameController.STATE start() {


        arena.startBgMusic();

        while (arena.playerAlive()) {

            try {

                screen.clear();
                new GameViewer().draw(screen, graphics, arena.getArenaModel(), arena.getArenaViewer());
                screen.refresh();

                KeyStroke key = screen.pollInput();

                runGame = arena.processKey(key, screen);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!runGame) {
                //Pause game
                arena.pauseBgMusic();

                return GameController.STATE.PAUSE;

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

            if (!arena.playerAlive()) return GameController.STATE.DEATH;
        }


        return null;
    }

}
