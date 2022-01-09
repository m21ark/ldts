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
    private final MenuViewer menuViewer;
    private final GameViewer gameViewer;
    private KeyStroke key;


    public GameController(Dimensions dimensions) throws IOException, URISyntaxException, FontFormatException {

        this.screen = new ScreenFactory().getScreen(dimensions, 26);
        this.graphics = screen.newTextGraphics();
        this.arena = new ArenaController(dimensions);
        this.menuViewer = new MenuViewer(dimensions, "#3A656C", "#000000");
        this.gameViewer = new GameViewer();
    }


    public void run() throws IOException {

        //Title  Screen
        screen.clear();
        menuViewer.drawLoadingScreen(graphics);
        screen.refresh();

        while (true) {
            key = screen.readInput();

            if (key.getCharacter() != null) {
                String input = key.getCharacter().toString().toUpperCase();

                if (input.equals("S")) {
                    break;
                }

                if (input.equals("Q")) {
                    System.exit(0);
                }
            }

        }


        //Main Game Screen
        int gameLoopInt = 0;
        boolean validInput = true;

        while (true) {
            do {
                gameViewer.draw(screen, graphics, arena.getArenaModel(), arena.getArenaViewer());
                key = screen.pollInput();

                validInput = arena.processKey(key, screen);

                if (gameLoopInt % 50 == 0) {
                    arena.addRandomBlock(1);
                    arena.applyGravity();
                }
                if (gameLoopInt == 450) {
                    arena.addRandomCoin(1);
                    gameLoopInt = 0;
                }
                gameLoopInt++;

                arena.update();

            } while (validInput && arena.playerAlive());


            //Ending Screen
            screen.clear();
            menuViewer.drawDeathScreen(graphics, arena.getArenaModel().getPlayerScore());
            screen.refresh();

            while (true) {
                key = screen.readInput();
                if (key.getCharacter() != null) {
                    String input = key.getCharacter().toString().toUpperCase();

                    if (key.getCharacter() != null) {
                        if (input.equals("R")) {
                            arena.reloadArena();
                            break;
                        }
                        if (input.equals("Q")) {
                            System.exit(0);
                        }
                    }
                }


            }


        }


    }


}
